package com.fatec.interfriends.repository;

import com.fatec.interfriends.domain.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT DISTINCT p.* FROM product p " +
            "INNER JOIN product_categories pc ON pc.product_id = p.product_id " +
            "INNER JOIN product_size ps ON ps.product_product_id = p.product_id " +
            "WHERE pc.category_id IN ?1 AND ps.size_size_id IN ?2", nativeQuery = true)
    Page<Product> findDistinctByCategoriesInAndSizesIn(List<Long> categories, List<Long> sizes, Pageable pageable);

}
