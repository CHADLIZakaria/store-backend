package com.zchadli.myrestauservice.specification;

import com.zchadli.myrestauservice.entities.Product;
import com.zchadli.myrestauservice.entities.RestauUser;
import com.zchadli.myrestauservice.entities.Review;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

public class ReviewSpecification {
    public static Specification<Review> hasKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if(keyword==null || keyword.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%"+keyword+"%");
        };
    }
    public static Specification<Review> hasProduct(Long idProduct) {
        return  (root, query, criteriaBuilder) -> {
            if(idProduct == null) {
                return  criteriaBuilder.conjunction();
            }
            Join<Review, Product> products = root.join("product", JoinType.INNER);
            return criteriaBuilder.equal(products.get("id"), idProduct);
        };
    }

    public static  Specification<Review> isApproved(Boolean isApproved) {
        return (root, query, criteriaBuilder) -> {
            if(isApproved==null) {
                return  criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(criteriaBuilder.coalesce(root.get("isApproved"), false), isApproved);
        };
    }

    public static Specification<Review> hasUser(String username) {
        return (root, query, criteriaBuilder) -> {
            if(username == null || username.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Review, RestauUser> users = root.join("user", JoinType.INNER);
            return criteriaBuilder.equal(users.get("username"), username);
        };
    }

}
