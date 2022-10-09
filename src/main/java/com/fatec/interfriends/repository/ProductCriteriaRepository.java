package com.fatec.interfriends.repository;

import com.fatec.interfriends.domain.model.ProductModel;
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

    public Page<ProductModel> findAllWithFilters(ProductPage productPage, ProductSearchCriteria productSearchCriteria) {
        CriteriaQuery<ProductModel> productModelCriteriaQuery = criteriaBuilder.createQuery(ProductModel.class);
        Root<ProductModel> productModelRoot = productModelCriteriaQuery.from(ProductModel.class);

        Predicate predicate = getPredicate(productSearchCriteria, productModelRoot);
        setOrder(productPage, productModelCriteriaQuery, productModelRoot);

        productModelCriteriaQuery.where(predicate);

        TypedQuery<ProductModel> productModelTypedQuery = entityManager.createQuery(productModelCriteriaQuery);

        productModelTypedQuery.setFirstResult(productPage.getPageNumber() * productPage.getPageSize());
        productModelTypedQuery.setMaxResults(productPage.getPageSize());

        return new PageImpl(productModelTypedQuery.getResultList(), getPageable(productPage), getProductsCount(predicate));
    }

    private Predicate getPredicate(ProductSearchCriteria productSearchCriteria, Root<ProductModel> productModelRoot) {
        List<Predicate> predicateList = new ArrayList();

        if (Objects.nonNull(productSearchCriteria.getName())) {
            predicateList.add(criteriaBuilder.like(productModelRoot.get("name"), "%" + productSearchCriteria.getName() + "%"));
            predicateList.add(criteriaBuilder.like(productModelRoot.get("description"), "%" + productSearchCriteria.getName() + "%"));
        }

        return criteriaBuilder.or(predicateList.toArray(new Predicate[0]));
    }

    private void setOrder(ProductPage productPage, CriteriaQuery<ProductModel> productModelCriteriaQuery, Root<ProductModel> productModelRoot) {
        if (productPage.getSortDirection().isAscending()) {
            productModelCriteriaQuery.orderBy(criteriaBuilder.asc(productModelRoot.get(productPage.getSortBy())));
        } else {
            productModelCriteriaQuery.orderBy(criteriaBuilder.desc(productModelRoot.get(productPage.getSortBy())));
        }
    }

    private Pageable getPageable(ProductPage productPage) {
        Sort sort = Sort.by(productPage.getSortDirection(), productPage.getSortBy());
        return PageRequest.of(productPage.getPageNumber(),productPage.getPageSize(), sort);
    }

    private long getProductsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<ProductModel> countRoot = countQuery.from(ProductModel.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
