package com.project.welltrip.domain;

import java.util.Date;

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
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long reservationId;
	
	private Date startDate;
	
	private Date endDate;
	
	private String departure;
	
	private String arrival;
	
	private int type;
	
	// TODOs : userId 와 외래키로 연결하기 위해서는 추가
	// private int or long userId
}
