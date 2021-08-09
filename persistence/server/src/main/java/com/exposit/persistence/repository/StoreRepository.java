package com.exposit.persistence.repository;

import com.exposit.core.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {
}
