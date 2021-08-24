package com.exposit.persistence.repository;

import com.exposit.persistence.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {
    boolean existsByName(String name);

    boolean existsByAddress(String address);
}
