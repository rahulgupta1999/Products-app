package com.rakuten.training;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest_WithMocitoAnnotations {
	
	@Mock
	ProductDAO mockDAO;
	
	@Test
	void createNewProduct_Must_Return_Id_When_Product_Value_GTEQ_MinValue() {
		// AAA
		// Arrange
		ProductServiceImpl objUnderTest = new ProductServiceImpl();
		Product argToMethod = new Product("test", 10000, 2);
		Product returnedByMethod = new Product("test", 10000, 2);
		returnedByMethod.setId(1);
		
		Mockito.when(mockDAO.save(argToMethod)).thenReturn(returnedByMethod);
		objUnderTest.setDao(mockDAO);
		// Act
		int id = objUnderTest.createNewProduct(argToMethod);
		// Assert
		assertTrue(id > 0);
	}
	

}