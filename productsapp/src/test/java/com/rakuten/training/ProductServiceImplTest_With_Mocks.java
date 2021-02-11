package com.rakuten.training;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductServiceImpl;

public class ProductServiceImplTest_With_Mocks {

	@Test
	void createNewProduct_Must_Return_Id_When_Product_Value_GTEQ_MinValue() {
		// AAA
		// Arrange
		ProductServiceImpl objUnderTest = new ProductServiceImpl();
		Product argToMethod = new Product("test", 10000, 2);
		Product returnedByMethod = new Product("test", 10000, 2);
		returnedByMethod.setId(1);

		ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
		Mockito.when(mockDAO.save(argToMethod)).thenReturn(returnedByMethod);
		objUnderTest.setDao(mockDAO);
		// Act
		int id = objUnderTest.createNewProduct(argToMethod);
		// Assert
		assertTrue(id > 0);

	}

	@Test
	public void createNewProduct_Must_Throw_When_Product_Value_LT_MinValue() {
		// AAA
		// Arrange
		ProductServiceImpl objUnderTest = new ProductServiceImpl();
		Product argToMethod = new Product("test", 9999, 1);

		Assertions.assertThatThrownBy(() -> {
			objUnderTest.createNewProduct(argToMethod);
		}).isInstanceOf(IllegalArgumentException.class);

	}
	
	@Test
	public void removeExisting_Must_Delete_Product_When_Existing_Product_Value_LT_100k() {
		//Arrange
		ProductServiceImpl objUnderTest = new ProductServiceImpl();
		int id = 1;
		Product returnedByMock = new Product("test", 10000, 2);
		returnedByMock.setId(id);
		ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
		Mockito.when(mockDAO.findById(id)).thenReturn(returnedByMock);
		objUnderTest.setDao(mockDAO);
		//Act
		objUnderTest.removeExisting(id);
		Mockito.verify(mockDAO).deleteById(id);
	}
	
}