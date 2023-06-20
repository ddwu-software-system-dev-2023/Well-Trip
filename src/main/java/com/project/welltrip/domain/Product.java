package com.project.welltrip.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long prdId;
	
	private String prdCode;
	
	private String prdAir; 
	
	// TODO : Date(LoaclDataTime, TimeStamp) 로 바꿀 것!!
	private String departDateTime;
	
	// TODO : Date(LoaclDataTime, TimeStamp) 로 바꿀 것!!
	private String arriveDateTime;
	
	private String departure;
	
	private String arrival;
	
	private int price;
}
