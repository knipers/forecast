package com.weather.forecast.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.weather.forecast.domains.City;
import com.weather.forecast.dto.CityDTO;
import com.weather.forecast.services.CityService;

@RestController
public class CityController {
	
	@Autowired
	private CityService service;
	
	@GetMapping("/city")
	public ResponseEntity<List<CityDTO>> listAll() {
		List<City> list = service.findAll();
		List<CityDTO> cityList = list.stream().map(city -> new CityDTO(city)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cityList);
	}
	
	@GetMapping("/city/{id}")
	public ResponseEntity<CityDTO> findById(@PathVariable Long id) {
		City city = service.findById(id);
		return ResponseEntity.ok().body(new CityDTO(city));
	}
	
	@PostMapping("/city")
	public ResponseEntity<Void> registerCity(@RequestBody CityDTO cityDTO) {
		City city = service.fromDTO(cityDTO); 
		city = service.registerCity(city);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(city.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@DeleteMapping("/city/{id}")
	public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
		service.deleteCity(id);
		return ResponseEntity.noContent().build();				
	}
}
