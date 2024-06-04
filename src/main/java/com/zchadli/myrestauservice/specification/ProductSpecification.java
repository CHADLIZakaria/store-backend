package com.zchadli.myrestauservice.specification;

import com.zchadli.myrestauservice.entities.Category;
import com.zchadli.myrestauservice.entities.Product;
import com.zchadli.myrestauservice.entities.RestauUser;
import com.zchadli.myrestauservice.entities.Review;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> hasId(Long id) {
        return (root, query, criteriaBuilder) -> {
          if(id==null || id < 0) {
              return criteriaBuilder.conjunction();
          }
          return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    public static Specification<Product> inFavorite(Long idUser) {
        return (root, query, criteriaBuilder) -> {
            Join<Product, RestauUser> products = root.join("userFavoriteProduct", JoinType.INNER);
            return criteriaBuilder.equal(products.get("id"), idUser);
        };
    }
    public static Specification<Product> hasCategory(List<Integer> ids) {
        return (root, query, criteriaBuilder) -> {
          if(ids == null || ids.isEmpty()) {
              return criteriaBuilder.conjunction();
          }
          Join<Product, Category> categories = root.join("category", JoinType.INNER);
          return categories.get("id").in(ids);
        };
    }
    public static Specification<Product> hasKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if(keyword == null || keyword.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%"+keyword+"%");
        };
    }
    public static Specification<Product> minPrice(Double minPrice) {
        return (root, query, criteriaBuilder) -> {
            if(minPrice == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.gt(root.get("price"), minPrice);
        };
    }
    public static Specification<Product> maxPrice(Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if(maxPrice == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lt(root.get("price"), maxPrice);
        };
    }
    public static Specification<Product> review(Integer review) {
        return  (root, query, criteriaBuilder) -> {
            if(review == null) {
                return  criteriaBuilder.conjunction();
            }
            Join<Product, Review> reviews = root.join("reviews", JoinType.INNER);
            return criteriaBuilder.equal(reviews.get("rating"), review);
        };
    }

}
