package com.daniellsantiago.fooddeliveryapi.infrastructure.repository.spec;

import com.daniellsantiago.fooddeliveryapi.domain.filter.OrderFilter;
import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class OrderSpecs {

    public static Specification<Order> usingFilter(OrderFilter filter) {
        return (root, query, builder) -> {
            if(Order.class.equals(query.getResultType())){
                root.fetch("restaurant").fetch("cuisine");
                root.fetch("customer");
            }
            var precidates = new ArrayList<Predicate>();

            if(filter.getCustomerId() != null) {
                precidates.add(builder.equal(root.get("customer"), filter.getCustomerId()));
            }

            if(filter.getRestaurantId() != null) {
                precidates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
            }

            if(filter.getInitialCreationDate() != null) {
                precidates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), filter.getInitialCreationDate()));
            }

            if(filter.getEndCreationDate() != null) {
                precidates.add(builder.lessThanOrEqualTo(root.get("createdAt"), filter.getEndCreationDate()));
            }
            return builder.and(precidates.toArray(new Predicate[0]));
        };
    }
}
