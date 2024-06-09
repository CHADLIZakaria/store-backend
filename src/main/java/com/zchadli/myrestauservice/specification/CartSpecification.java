package com.zchadli.myrestauservice.specification;

import com.zchadli.myrestauservice.entities.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

public class CartSpecification {
    public static Specification<Cart> hasUsername(String username) {
        return (root, query, criteriaBuilder) -> {
          if(username == null || username.trim().isEmpty()) {
              return criteriaBuilder.conjunction();
          }
          Join<Cart, StoreUser> cartUsers = root.join("user", JoinType.INNER);
          return criteriaBuilder.equal(cartUsers.get("username"), username);
        };
    }

}
