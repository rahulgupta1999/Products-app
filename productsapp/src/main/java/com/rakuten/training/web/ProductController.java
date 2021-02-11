package com.rakuten.training.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;

@RestController
public class ProductController {
	
	
	
	ProductService service;
	@Autowired
	public void setService(ProductService service)
	{
		this.service=service;
	}
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return service.findAll();
	}

	@GetMapping("/products/{idPathVariable}")
	public ResponseEntity<Product> getProductById(@PathVariable("idPathVariable") int id) {
		Product p = service.findById(id);
		if(p != null) {
			return new ResponseEntity<Product>(p, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/products")
	public ResponseEntity addNewProduct(@RequestBody Product toBeAdded) {
		try {
			int id = service.createNewProduct(toBeAdded);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/products/"+id));
			return new ResponseEntity(toBeAdded, headers, HttpStatus.CREATED);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	@DeleteMapping("/products/{idPathVariable}")
	public ResponseEntity deleteProduct(@PathVariable("idPathVariable") int id){
		try
		{
		service.removeExisting(id);
		return new ResponseEntity<>(HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}