package co.ipsita.product.app.domain;

import lombok.Value;
import org.springframework.data.jpa.domain.Specification;

@Value
public
class ProductQuery {

    private final SearchCriteria searchCriteria;

    public ProductQuery(SearchCriteria searchCriteria){
        this.searchCriteria = searchCriteria;
    }

    public Specification<Product> toSpecification() {

        return new ProductQuerySpecification(this);
    };


}
