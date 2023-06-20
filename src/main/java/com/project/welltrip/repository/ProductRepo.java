package com.project.welltrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.welltrip.domain.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{
	
	List<Product> findByDepartureAndArrival(String departure, String Arrival);
}
