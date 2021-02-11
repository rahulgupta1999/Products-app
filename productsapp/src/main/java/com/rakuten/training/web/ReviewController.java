package com.rakuten.training.web;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;
import com.rakuten.training.service.ProductService;
import com.rakuten.training.service.ReviewService;

@RestController
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/products/{pid}/reviews")
	public ResponseEntity getAllReviewsForAProduct(@PathVariable("pid") int productId) {
		Product p = productService.findById(productId);
		if(p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Review> reviews = reviewService.findByProduct_Id(productId);
		return new ResponseEntity(reviews, HttpStatus.OK);
	}

	@PostMapping("/products/{pid}/reviews")
	public ResponseEntity addReiewToProduct(@PathVariable("pid") int productId,@RequestBody Review toBeAdded) {
		Product p = productService.findById(productId);
		if(p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Review added = reviewService.addReviewToProduct(toBeAdded, productId);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/products/"+productId+"/reviews/"+added.getId()));
		return new ResponseEntity(added, headers, HttpStatus.CREATED);
	}
}
