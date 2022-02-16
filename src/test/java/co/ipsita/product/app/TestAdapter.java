package co.ipsita.product.app;

import co.ipsita.product.app.domain.PostResponse;
import co.ipsita.product.app.domain.Product;
import co.ipsita.product.app.domain.ProductDto;
import co.ipsita.product.app.service.IProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.ValidationException;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestAdapter {
    @Autowired
    IProductService service;


    @Test
    public  void test(){
       PostResponse productList= service.getAllProducts(0,3);
        Assert.assertEquals(productList.getContent().size(),3);
    }

    @Test
    public  void testAdd(){
        Product product= null;
        try {
            product = service.addProduct(getProcut());
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(product);
    }

    public Product getProcut(){
        Product dto = new Product();
        dto.setProductId(1L);
        dto.setName("ipsita");
        dto.setPrice(10.1);
        dto.setCategory(Category.PUR.getCategory());
        return dto;

    }
}
