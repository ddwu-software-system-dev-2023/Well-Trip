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
@Table(name = "airplane")
public class Airplane {

	@Id
	private long airplaneId;
	
	private long reservationId;
	
	private long classId;
	
	private String airplaneName;
	
	private int capacity;
	
	private String airplaneCode;
	
	private int departureTime;
	
	private int arrivalTime;
}
