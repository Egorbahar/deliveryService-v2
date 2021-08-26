package com.exposit.persistence.repository;

import com.exposit.persistence.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Long> {

    boolean existsByAddress(String address);

    @Query("SELECT store FROM Store store  JOIN store.products product " +
            "WHERE product.price = (SELECT MIN(product.price) FROM product WHERE product.name =:productName )")
    List<Store> findStoreByMinPriceOfProductName(@Param("productName") String productName);
    @Query("SELECT store FROM Store store JOIN store.products product" +
            " WHERE product.name =:productName AND product.quantity > :quantity")
    List<Store> findAllByProductNameAndProductQuantityGreaterThan(@Param("productName") String productName, @Param("quantity") int quantity);
}
