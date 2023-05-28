package com.project.welltrip.domain;

import java.util.Date;
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
@Table(name = "reservation")
public class Reservation {

	@Id
	private long reservationId;
	
	private Date startDate;
	
	private Date endDate;
	
	private String departure;
	
	private String arrival;
	
	private int type;
}
