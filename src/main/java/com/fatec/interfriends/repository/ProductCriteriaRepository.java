package com.fatec.interfriends.repository;

import com.fatec.interfriends.domain.model.Product;
import com.fatec.interfriends.repository.query.ProductPage;
import com.fatec.interfriends.repository.query.ProductSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ProductCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Product> findAllWithFilters(ProductPage productPage, ProductSearchCriteria productSearchCriteria) {
        CriteriaQuery<Product> productCriteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = productCriteriaQuery.from(Product.class);

        Predicate predicate = getPredicate(productSearchCriteria, productRoot);
        setOrder(productPage, productCriteriaQuery, productRoot);

        productCriteriaQuery.where(predicate);

        TypedQuery<Product> productTypedQuery = entityManager.createQuery(productCriteriaQuery);

        productTypedQuery.setFirstResult(productPage.getPageNumber() * productPage.getPageSize());
        productTypedQuery.setMaxResults(productPage.getPageSize());

        return new PageImpl(productTypedQuery.getResultList(), getPageable(productPage), getProductsCount(predicate));
    }

    private Predicate getPredicate(ProductSearchCriteria productSearchCriteria, Root<Product> productRoot) {
        List<Predicate> predicateList = new ArrayList();

        if (Objects.nonNull(productSearchCriteria.getName())) {
            predicateList.add(criteriaBuilder.like(productRoot.get("name"), "%" + productSearchCriteria.getName() + "%"));
            predicateList.add(criteriaBuilder.like(productRoot.get("description"), "%" + productSearchCriteria.getName() + "%"));
        }

        return criteriaBuilder.or(predicateList.toArray(new Predicate[0]));
    }

    private void setOrder(ProductPage productPage, CriteriaQuery<Product> productCriteriaQuery, Root<Product> productRoot) {
        if (productPage.getSortDirection().isAscending()) {
            productCriteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get(productPage.getSortBy())));
        } else {
            productCriteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get(productPage.getSortBy())));
        }
    }

    private Pageable getPageable(ProductPage productPage) {
        Sort sort = Sort.by(productPage.getSortDirection(), productPage.getSortBy());
        return PageRequest.of(productPage.getPageNumber(),productPage.getPageSize(), sort);
    }

    private long getProductsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Product> countRoot = countQuery.from(Product.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
