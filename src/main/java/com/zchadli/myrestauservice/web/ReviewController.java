package com.zchadli.myrestauservice.web;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zchadli.myrestauservice.business.service.ReviewService;
import com.zchadli.myrestauservice.dto.ReviewDto;
import com.zchadli.myrestauservice.entities.PaginationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class ReviewController {

    private final ReviewService reviewService;

    @RequestMapping(value="/review", method = RequestMethod.POST)
    public ReviewDto save(@RequestBody ReviewDto reviewDto) {
        return reviewService.save(reviewDto);
    }

    @RequestMapping(value="/product/{idProduct}/reviews", method = RequestMethod.GET)
    public List<ReviewDto> findAllByProduct(@PathVariable Long idProduct) {
        return reviewService.findAllByProduct(idProduct);
    }

    @RequestMapping(value="/reviews/search", method = RequestMethod.GET)
    public PaginationResponse search(
        @RequestParam(name="page", defaultValue = "0") int page,
        @RequestParam(name="size", defaultValue = "5") int size,
        @RequestParam(name="keyword", defaultValue = "") String keyword,
        @RequestParam(name="idProduct", required = false) Long idProduct,
        @RequestParam(name="username", defaultValue = "") String username,
        @RequestParam(name="approved", defaultValue = "-1") int approved
    ) {
        return reviewService.findSearch(page, size, keyword, idProduct, username, approved);
    }
    
    @RequestMapping(value="/review/{idReview}", method = RequestMethod.DELETE)
    public void findAll(@PathVariable Long idReview) {
        reviewService.deleteById(idReview);
    }

    @RequestMapping(value="/review/toggleApprove/{idReview}", method = RequestMethod.PUT)
    public ReviewDto toggleApproveReview(@PathVariable Long idReview) {
        return reviewService.toggleApproveReview(idReview);
    }
    
}
