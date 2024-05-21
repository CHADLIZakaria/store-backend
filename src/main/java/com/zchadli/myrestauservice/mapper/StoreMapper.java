package com.zchadli.myrestauservice.mapper;

import java.util.List;

import com.zchadli.myrestauservice.dto.*;
import org.mapstruct.Mapper;

import com.zchadli.myrestauservice.entities.Category;
import com.zchadli.myrestauservice.entities.Product;
import com.zchadli.myrestauservice.entities.RestauUser;
import com.zchadli.myrestauservice.entities.Review;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    Category toCategory(CategoryDto categoryDto);
    CategoryDto toCategoryDto(Category category);
    List<Category> toCategories(List<CategoryDto> categoriesDto);
    List<CategoryDto> toCategoriesDto(List<Category> categories);

    Product toProduct(ProductDto productDto);
    ProductDto toProductDto(Product product);
    List<Product> toProducts(List<ProductDto> productsDto);
    List<ProductDto> toProductsDto(List<Product> products);

    RestauUser toUser(UserDto userDto);
    UserDto toUserDto(RestauUser user);
    List<RestauUser> toUsers(List<UserDto> usersDto);
    List<UserDto> toUsersDto(List<RestauUser> users);

    Review toReview(ReviewDto reviewDto);
    ReviewDto toReviewDto(Review review);
    List<Review> toReviews(List<ReviewDto> reviewsDto);
    List<ReviewDto> toReviewsDto(List<Review> reviews);
}
