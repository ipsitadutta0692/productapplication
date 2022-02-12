package co.ipsita.product.app.domain;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class ProductQuerySpecification implements Specification<Product> {

    private final ProductQuery productQuery;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new LinkedList<>();
        SearchCriteria criteria = productQuery.getSearchCriteria();

        if (criteria.getKey().equals("name")) {
            predicates.add(criteriaBuilder.equal(root.get("name"), criteria.getValue()));
            return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                    .distinct(true)
                    .getRestriction();
        }
        if (criteria.getKey().equals("currency")) {
            predicates.add(criteriaBuilder.equal(root.get("currency"), criteria.getValue()));
            return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                    .distinct(true)
                    .getRestriction();
        }
        return null;
    }
}
