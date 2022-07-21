package com.zchadli.myrestauservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.zchadli.myrestauservice.dto.CategoryDto;
import com.zchadli.myrestauservice.dto.ProductDto;
import com.zchadli.myrestauservice.dto.ReviewDto;
import com.zchadli.myrestauservice.dto.SizeDto;
import com.zchadli.myrestauservice.dto.UserDto;
import com.zchadli.myrestauservice.entities.Category;
import com.zchadli.myrestauservice.entities.Product;
import com.zchadli.myrestauservice.entities.RestauUser;
import com.zchadli.myrestauservice.entities.Review;
import com.zchadli.myrestauservice.entities.Size;

@Mapper(componentModel = "spring")
public interface RestauMapper {
    
    Category toCategory(CategoryDto categoryDto);
    CategoryDto toCategoryDto(Category category);
    List<Category> toCategories(List<CategoryDto> categoriesDto);
    List<CategoryDto> toCategoriesDto(List<Category> categories);

    //@Mapping(source = "idCategory", target = "category.id")
    Product toProduct(ProductDto productDto);
    //@Mapping(source = "category.id", target = "idCategory")
    ProductDto toProductDto(Product product);
    List<Product> toProducts(List<ProductDto> productsDto);
    List<ProductDto> toProductsDto(List<Product> products);

    Size toSize(SizeDto sizeDto);
    SizeDto toSizeDto(Size size);


    RestauUser toUser(UserDto userDto);
    UserDto toUserDto(RestauUser user);
    List<RestauUser> toUsers(List<UserDto> usersDto);
    List<UserDto> toUsersDto(List<RestauUser> users);

    Review toReview(ReviewDto reviewDto);
    ReviewDto toReviewDto(Review review);
    List<Review> toReviews(List<ReviewDto> reviewsDto);
    List<ReviewDto> toReviewsDto(List<Review> reviews);
    
}
