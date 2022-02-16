package co.ipsita.product.app.domain;

import co.ipsita.product.app.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
;

@Data
@Entity
@Table(name="product_application")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private double price;

    @Column(name="currency")
    private String currency;

    @Column(name="category")
    private String category;


    @Column(name="modified_date")
    private Timestamp modifiedDate;

    public Product(Long productId, String name, double price, String currency, String category, Timestamp modifiedDate) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.category = category;
        this.modifiedDate = modifiedDate;
    }
}
