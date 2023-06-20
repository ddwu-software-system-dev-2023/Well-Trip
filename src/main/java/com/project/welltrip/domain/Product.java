package com.project.welltrip.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Setter
@Getter
@Entity
@Table(name = "airplaneclass")
public class AirplaneClass {

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
