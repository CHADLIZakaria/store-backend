package com.zchadli.myrestauservice.mapper;

import java.util.List;

import com.zchadli.myrestauservice.dto.*;
import com.zchadli.myrestauservice.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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

    StoreUser toUser(UserDto userDto);
    UserDto toUserDto(StoreUser user);
    List<StoreUser> toUsers(List<UserDto> usersDto);
    List<UserDto> toUsersDto(List<StoreUser> users);

    Review toReview(ReviewDto reviewDto);
    ReviewDto toReviewDto(Review review);
    List<Review> toReviews(List<ReviewDto> reviewsDto);
    List<ReviewDto> toReviewsDto(List<Review> reviews);
    @Mapping(source = "cartProducts", target = "products")
    @Mapping(source = "user.id", target = "userId")
    CartDto toCartDto(Cart cart);
    List<CartDto> toCartsDto(List<Cart> cart);
    @Mapping(source = "product.id", target = "idProduct")
    @Mapping(source = "product.title", target = "title")
    @Mapping(source = "product.imagePath", target = "imagePath")
    @Mapping(source = "product.price", target = "price")
    @Mapping(source = "product.category.name", target = "categoryName")
    CartProductDto toCartProductDto(CartProduct cartProduct);
    List<CartProductDto> toCartProductsDto(List<CartProduct> cartProduct);


}
