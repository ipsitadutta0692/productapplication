package co.ipsita.product.app.service;

import co.ipsita.product.app.domain.PostResponse;
import co.ipsita.product.app.domain.Product;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface IProductService {
    Product findProductById(Long id);
    PostResponse getAllProducts(int pageNo, int pageSize);
    Product updateProduct(Product product);
    Product addProduct(Product product) throws ValidationException;
}
