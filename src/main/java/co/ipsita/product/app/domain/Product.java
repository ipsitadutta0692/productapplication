package co.ipsita.product.app.domain;

import co.ipsita.product.app.Category;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
;

@Data
@Entity
@Table(name="product_application")
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




}
