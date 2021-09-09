package com.exposit.persistence.specification;

import com.exposit.persistence.entity.Store;
import org.springframework.data.jpa.domain.Specification;

public class StoreSpecification {
    public static Specification<Store> storeByName(final String storeName) {
        return (Specification<Store>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), storeName);
    }

    public static Specification<Store> storeByAddress(final String address) {
        return (Specification<Store>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("address"), address);
    }

}
