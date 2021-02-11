//package com.rakuten.training;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.rakuten.training.dal.ProductDAO;
//import com.rakuten.training.dal.ProductDAOInMemImpl;
//import com.rakuten.training.service.ProductService;
//import com.rakuten.training.service.ProductServiceImpl;
//import com.rakuten.training.ui.ProductConsoleUI;
//
//@Configuration
//public class ProductsAppConfiguration {
//
//	@Bean
//	public ProductDAO daoObj() {
//		ProductDAOInMemImpl dao = new ProductDAOInMemImpl();
//		return dao;
//	}
//	
//	@Bean
//	public ProductService serviceObj() {
//		ProductServiceImpl service = new ProductServiceImpl();
//		service.setDao(daoObj());
//		return service;
//	}
//	
//	@Bean
//	public ProductConsoleUI uiObj() {
//		ProductConsoleUI ui = new ProductConsoleUI();
//		ui.setService(serviceObj());
//		return ui;
//	}
//}
