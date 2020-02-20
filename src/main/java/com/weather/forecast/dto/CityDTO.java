package com.weather.forecast.dto;

import com.weather.forecast.domains.City;

public class CityDTO {
	
	private Long id;
	private String name;
	
	public CityDTO() {
		
	}
	
	public CityDTO(City obj) {
		this.id = obj.getId();
		this.name = obj.getName();		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
