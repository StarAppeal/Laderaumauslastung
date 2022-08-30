package de.starappeal.laderaumauslastung.api;

import org.apache.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record SearchSpecification<T>(SearchRequest searchRequest) implements Specification<T> {

    @Serial
    private static final long serialVersionUID = -5739252582115415172L;

    private static final Logger logger = Logger.getLogger(SearchSpecification.class);

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();

        for (FilterRequest filter : this.searchRequest.filters()) {
            logger.info("Filter: %s %s %s".formatted(filter.getKey(), filter.getOperator().toString(), filter.getValue()));
            predicate = filter.getOperator().build(root, cb, filter, predicate);
        }

        List<Order> orders = new ArrayList<>();
        for (SortRequest sort : this.searchRequest.sorts()) {
            orders.add(sort.direction().build(root, cb, sort));
        }

        query.orderBy(orders);
        return predicate;
    }

    public static Pageable getPageable(Integer page, Integer size) {
        return PageRequest.of(Objects.requireNonNullElse(page, 0), Objects.requireNonNullElse(size, 100));
    }

}
