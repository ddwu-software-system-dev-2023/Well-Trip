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
	private long classId;
	
	private long airplaneId;
	
	private int type;
	
	private int price;
	
	private int passengerType;
}
