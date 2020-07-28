package com.daniellsantiago.fooddeliveryapi.infrastructure.service;

import com.daniellsantiago.fooddeliveryapi.domain.filter.DailySaleFilter;
import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import com.daniellsantiago.fooddeliveryapi.domain.model.OrderStatus;
import com.daniellsantiago.fooddeliveryapi.domain.model.dto.DailySale;
import com.daniellsantiago.fooddeliveryapi.domain.service.SalesQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SalesQueryServiceImpl implements SalesQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<DailySale> consultDailySales(DailySaleFilter filter, String timeOffset) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(DailySale.class);
        var root = query.from(Order.class);
        var predicates = new ArrayList<Predicate>();

        //Mysql function: 'CONVERT_TZ (dt, from_tz,to_tz) '
        //https://www.w3resource.com/mysql/date-and-time-functions/mysql-convert_tz-function.php
        var functionConvertTzCreatedAt = builder.function(
                "convert_tz", Date.class, root.get("createdAt"),
                builder.literal("+00:00"), builder.literal(timeOffset));

        //Mysql function: DATE()
        //extracts the date part from a expression.
        var functionDateCreatedAt = builder.function(
                "date", Date.class, functionConvertTzCreatedAt);

        var selection = builder.construct(DailySale.class,
                functionDateCreatedAt,
                builder.count(root.get("id")),
                builder.sum(root.get("totalPrice")));

        if (filter.getRestaurantId() != null) {
            predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
        }

        if (filter.getInitialCreationDate() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"),
                    filter.getInitialCreationDate()));
        }

        if (filter.getEndCreationDate() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"),
                    filter.getEndCreationDate()));
        }

        predicates.add(root.get("status").in(
                OrderStatus.CONFIRMED, OrderStatus.DELIVERED));

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(functionDateCreatedAt);

        return manager.createQuery(query).getResultList();
    }
}
