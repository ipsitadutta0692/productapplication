package co.ipsita.product.app;

import co.ipsita.product.app.domain.PostResponse;
import co.ipsita.product.app.domain.Product;
import co.ipsita.product.app.domain.ProductDto;
import co.ipsita.product.app.service.IProductService;
import org.junit.Assert;
import org.junit.Before;
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
            product = service.addProduct(getProduct());
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(product);
    }


    @Test
    public  void testUpdate(){
        Product product= null;
        try {
            product = service.addProduct(getProduct());
            product.setName("dutta");
            product.setCategory(Category.CAS.getCategory());
             service.updateProduct(product);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(product.getCategory(),Category.CAS.getCategory());
    }


    @Test
    public  void testGetBYID(){
        Product result= null;
        try {
            Product   product = service.addProduct(getProduct());

            result= service.findProductById(product.getProductId());
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(result);
    }



    @Test
    public  void testGetAll() throws ValidationException {
        Product result= null;
        service.addProduct(getProduct());
           PostResponse response = service.getAllProducts(1,3);

        Assert.assertEquals(response.getContent().size(),1);
    }



    public Product getProduct(){
        Product dto = new Product();
        dto.setProductId(1L);
        dto.setName("ipsita");
        dto.setPrice(10.1);
        dto.setCategory(Category.PUR.getCategory());
        return dto;

    }
}
