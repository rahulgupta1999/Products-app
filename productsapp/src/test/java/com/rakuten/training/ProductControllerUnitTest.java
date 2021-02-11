package com.rakuten.training;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;
import com.rakuten.training.web.ProductController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ProductController.class})
class ProductControllerUnitTest {
	
	@MockBean
	ProductService mockService;

	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	void testGetProductById() throws Exception {
		//AAA
		//Arrange
		int id = 1;
		Product dataReturnedByMock = new Product("test", 12345, 2);
		dataReturnedByMock.setId(id);
		
		Mockito.when(mockService.findById(id)).thenReturn(dataReturnedByMock);
		
		//Act and Assert
		mockMvc
			.perform(
					MockMvcRequestBuilders.get("/products/{id}", id)
					.accept(MediaType.APPLICATION_JSON)
			).andExpect(
					MockMvcResultMatchers.status().isOk()
			).andExpect(jsonPath("$.id", CoreMatchers.is(1)));
		
	}

}