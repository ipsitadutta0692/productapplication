package co.ipsita.product.app.service;

import co.ipsita.product.app.domain.*;
import co.ipsita.product.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    private  final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @Override
    public Product findProductById(Long productId) {
        Product result = productRepository.findProductByProductId(productId);
        return result;
    }

    @Override
    public PostResponse getAllProducts( int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Product> listOfProduct = productRepository.findAll(pageable);
        List<Product> productList= listOfProduct.getContent();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(productList);
        postResponse.setPageNo(listOfProduct.getNumber());
        postResponse.setPageSize(listOfProduct.getSize());
        postResponse.setTotalElements(listOfProduct.getSize());
        postResponse.setLast(listOfProduct.isLast());

         return postResponse;
    }

    @Override
    public Product updateProduct(Product product) throws ValidationException{


        if(productRepository.existsById(product.getProductId())) {
            return productRepository.save(product);
        }else{
            throw new ValidationException("ID Does not Exist","ID Does not Exist");
        }


    }

    @Override
    public Product addProduct(Product product) throws ValidationException {
        boolean checkIdExist = doesIdExist(product.getProductId());
        if(checkIdExist){
        throw new ValidationException("Id Already Exist","Id Already Exist");
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> searchProduct(SearchCriteria criteria) {
        ProductQuery  productQuery = new ProductQuery(criteria);
        List<Product> product = productRepository.findAll(productQuery.toSpecification());
        return product;
    }

    private boolean doesIdExist(long id){
        Product product = productRepository.findProductByProductId(id);
        if(product!=null){
            return true;
        }else{
           return false;
        }
    }


}
