package com.weather.forecast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.weather.forecast.domains.Forecast;
import com.weather.forecast.services.ForecastService;

@RestController
public class ForecastController {
	
	@Autowired
	private ForecastService service;
	
	@GetMapping("forecast/{id}")
	public ResponseEntity<List<Forecast>> findById(@PathVariable Long id) {
		List<Forecast> list = service.getForecast(id);
		return ResponseEntity.ok().body(list);
	}
	
	
}
