package com.rakuten.training.dal;

import java.util.List;

import com.rakuten.training.domain.Review;

public interface RreviewDAO {

	Review save(Review r);

	Review findById(int id);

	List<Review> findByProduct_Id(int pid);

}