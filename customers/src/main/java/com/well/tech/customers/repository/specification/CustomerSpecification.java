package com.well.tech.customers.repository.specification;

import com.well.tech.customers.dto.CustomerFilterCriteria;
import com.well.tech.customers.entity.Customer;
import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class CustomerSpecification {

    public static Specification<Customer> build(CustomerFilterCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(criteria.name())) {
                predicates.add(cb.like(cb.lower(root.get("name")),
                        "%" + criteria.name().toLowerCase() + "%"));
            }

            if (StringUtils.hasText(criteria.cpf())) {
                predicates.add(cb.equal(root.get("cpf"), criteria.cpf()));
            }

            if (StringUtils.hasText(criteria.email())) {
                predicates.add(cb.equal(cb.lower(root.get("email")),
                        criteria.email().toLowerCase()));
            }

            if (StringUtils.hasText(criteria.phoneNumber())) {
                predicates.add(cb.equal(root.get("phoneNumber"), criteria.phoneNumber()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}