package com.well.tech.customers.repository.specification;

import com.well.tech.customers.dto.AddressFilterCriteria;
import com.well.tech.customers.entity.Address;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AddressSpecification {

    private AddressSpecification() {
    }

    public static Specification<Address> build(AddressFilterCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(criteria.street())) {
                predicates.add(cb.like(cb.lower(root.get("street")),
                        "%" + criteria.street().toLowerCase() + "%"));
            }

            if (StringUtils.hasText(criteria.city())) {
                predicates.add(cb.equal(cb.lower(root.get("city")),
                        criteria.city().toLowerCase()));
            }

            if (StringUtils.hasText(criteria.state())) {
                predicates.add(cb.equal(cb.lower(root.get("state")),
                        criteria.state().toLowerCase()));
            }

            if (StringUtils.hasText(criteria.zipCode())) {
                predicates.add(cb.equal(root.get("zipCode"), criteria.zipCode()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
