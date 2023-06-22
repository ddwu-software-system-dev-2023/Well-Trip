package com.project.welltrip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.welltrip.domain.Product;
import com.project.welltrip.repository.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	ProductRepo prdRepo;
	
	// 전체 행 조회
	public List<Product> doSelectAll() {
		return prdRepo.findAll();
	}
	
	// 특정 출발지와 도착지에 따른 조건 조회
	public List<Product> doSelectByDepartureAndArrival(String departure, String arrival) {
		return prdRepo.findByDepartureAndArrival(departure, arrival);
	}
	
	// prdId 에 따른 조건 조회
	public Product doSelectOneById(long prdId) {
		return prdRepo.findById(prdId).get();
	}
	
	// 행 삽입
	public void doInsert(Product prd) {
		prdRepo.save(prd);
	}
	
	// 행 수정
	public void doUpdate(Product prd) {
		prdRepo.save(prd);
	}
	
	// 행 삭제
	public void doDelete(long prdId) {
		prdRepo.deleteById(prdId);
	}
}
