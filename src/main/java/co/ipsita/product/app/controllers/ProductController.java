package co.ipsita.product.app.controllers;


import co.ipsita.product.app.domain.Product;
import co.ipsita.product.app.domain.ProductDto;
import co.ipsita.product.app.factory.ResponseFactory;
import co.ipsita.product.app.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.catalina.connector.ResponseFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

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
    public List<Product> getProductAll(){

        return service.getAllProducts();
    }



    @Operation(
            summary = "Update Product",
            description ="You can Update Specific Product details"
    )
    @RequestMapping(value = "/products/{id}",method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody ProductDto product, @PathVariable long id){

        Product product1 = new Product();
        product1.setId(id);
        product1.setName(product.getName());
        product1.setCategory(product.getCategory());
        product1.setPrice(product.getPrice());
        product1.setCurrency(product.getCurrency());
        Product response = service.updateProduct(product1);
        return response;
    }




    @Operation(
            summary = "Add Product",
            description ="You can Add New Product details"
    )
    @RequestMapping(value = "/products",method = RequestMethod.POST)
    public ResponseEntity addProduct(@RequestBody Product product) {
       try {
           Product response = service.addProduct(product);
           return ResponseFactory.createSuccessResponse(response);
       }catch (Exception e){
           return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
    }


}
