package co.ipsita.product.app.controllers;


import co.ipsita.product.app.Category;
import co.ipsita.product.app.domain.PostResponse;
import co.ipsita.product.app.domain.Product;
import co.ipsita.product.app.domain.ProductDto;
import co.ipsita.product.app.domain.SearchCriteria;
import co.ipsita.product.app.factory.ResponseFactory;
import co.ipsita.product.app.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    @Operation(
            summary = "Get Product By Id",
            description ="You can fetch specific Product"
    )
    @RequestMapping(value = "/products/{id}",method = RequestMethod.GET)
public ResponseEntity<Product> getProductById(@PathVariable long id){

        return ResponseFactory.createSuccessResponse(service.findProductById(id));
}

    @Operation(
            summary = "Get Product List",
            description ="You can fetch All Products Available"
    )
    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public PostResponse getProductAll(@RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
                                      @RequestParam(value="pageSize",defaultValue = "10",required = false) int pageSize){

        return service.getAllProducts(  pageNo, pageSize);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Update Product",
            description ="You can Update Specific Product details"
    )
    @RequestMapping(value = "/products/{id}",method = RequestMethod.PUT)
    public ResponseEntity updateProduct(@RequestBody ProductDto product, @PathVariable long id){

        Product product1 = covertDtoToEntityForUpdate(product,id);
       try {
           Product response = service.updateProduct(product1);
           return ResponseFactory.createSuccessResponse(response);
       }catch (Exception e){
           return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
    }




    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Add Product",
            description ="You can Add New Product details"
    )
    @RequestMapping(value = "/products",method = RequestMethod.POST)
    public ResponseEntity addProduct(@RequestBody ProductDto product) {
       try {

           Product product1 = covertDtoToEntity(product);

           Product response = service.addProduct(product1);
           return ResponseFactory.createSuccessResponse(response);
       }catch (Exception e){
           return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
    }




    @Operation(
            summary = "Search Product",
            description ="You can Search New Product details based on key value : name , currency"
    )
    @RequestMapping(value = "/products/searchProduct",method = RequestMethod.POST)
    public List<Product> searchProduct(@RequestBody SearchCriteria criteria) {


           List< Product> response = service.searchProduct(criteria);
            return response;

    }


    public Product covertDtoToEntity(ProductDto dto) throws  ValidationException{
        Product product = new Product();

        product.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));;
        product.setName(dto.getName());
        product.setCurrency(dto.getCurrency());
        if( EnumUtils.isValidEnum(Category.class, dto.getCategory().toUpperCase(Locale.ROOT))){
            product.setCategory(Category.valueOf(dto.getCategory()).getCategory());
        }else{
            throw  new ValidationException("Category Can only be CAS,PUR,PCS");
        }
        product.setProductId(0L);
        product.setPrice(dto.getPrice());
        return product;
    }

    public Product covertDtoToEntityForUpdate(ProductDto dto,Long id){
        Product product = new Product();
        product.setModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        product.setName(dto.getName());
        product.setCurrency(dto.getCurrency());
        product.setCategory(Category.valueOf(dto.getCategory()).getCategory());
        product.setProductId(id);
        product.setPrice(dto.getPrice());
        return product;
    }

}
