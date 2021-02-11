package com.rakuten.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.dal.ProductRepository;
import com.rakuten.training.domain.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	ProductDAO dao; // = new ProductDAOInMemImpl();
	//ProductRepository rep;
	@Autowired
	public void setDao(ProductDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public int createNewProduct(Product toBeCreated) {
		if(toBeCreated.getPrice() * toBeCreated.getQoh() >= 10000) {
			Product created = dao.save(toBeCreated);
			//rep.findByPrice(1000000);
			return created.getId();
		}else {
			throw new IllegalArgumentException("The product passed to create is not worth 10k");
		}
	}

	@Override
	public void removeExisting(int id) {
		Product existing = dao.findById(id);
		
		if(existing == null) {
			throw new IllegalArgumentException("product with id "+id+" not found!");
		}
		if(existing.getPrice() * existing.getQoh() >= 100000) {
			throw new IllegalStateException("Can't delete product when stock >= 100k");
		}
		dao.deleteById(id);
		
	}
	
	public void changePrice(int id,float newPrice) {
		Product p = dao.findById(id);
		p.setPrice(newPrice);
	}
	

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public Product findById(int id) {
		return dao.findById(id);
	}

}
