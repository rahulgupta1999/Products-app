package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.domain.Review;

@Repository
@Transactional
public class ReviewDAOJpaImpl implements RreviewDAO {

	@Autowired
	EntityManager em;
	
	@Override
	public Review save(Review r) {
		em.persist(r);
		return r;
	}
	
	@Override
	public Review findById(int id){
		return em.find(Review.class, id);
	}
	
	@Override
	public List<Review> findByProduct_Id(int pid){
		Query q = em.createQuery("select r from Review r where r.product.id=:productId");
		q.setParameter("productId", pid);
		return q.getResultList();
	}
}
