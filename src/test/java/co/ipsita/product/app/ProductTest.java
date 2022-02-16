package co.ipsita.product.app;

import co.ipsita.product.app.domain.Product;
import co.ipsita.product.app.repositories.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductTest {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    protected EntityManager entityManager;

    @Autowired
    private ProductRepository repo;

@Test
    public void findAll(){
        Product p = new Product(1L,"djjd",10.0,"ZAR","CAS", Timestamp.valueOf("2022-02-15 00:09:37.869") );

         repo.save(p);
        List<Product> result = repo.findAll();
        Assert.assertEquals(result.size(),4
        );
    }
}
