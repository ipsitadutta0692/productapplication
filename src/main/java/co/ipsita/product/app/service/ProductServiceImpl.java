package co.ipsita.product.app.service;

import co.ipsita.product.app.domain.Product;
import co.ipsita.product.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Product findProductById(Long id) {
        Product result = productRepository.findProductById(id);
        return result;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> listOfProduct = productRepository.findAll();
         return listOfProduct;
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product addProduct(Product product) throws ValidationException {
        boolean checkIdExist = doesIdExist(product.getId());
        if(checkIdExist){
        throw new ValidationException("id","this Id Already Exist");
        }
        return productRepository.save(product);
    }

    private boolean doesIdExist(long id){
        Product product = productRepository.findProductById(id);
        if(product!=null){
            return true;
        }else{
           return false;
        }
    }


}
