package com.weather.forecast.services;

import java.text.Normalizer;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.weather.forecast.consumer.OpenWheatherApiComponent;
import com.weather.forecast.domains.City;
import com.weather.forecast.dto.CityDTO;
import com.weather.forecast.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private OpenWheatherApiComponent api;
	
	@Autowired
	private CityRepository repository;
	
	public List<City> findAll() {
		return repository.findAll();
	}
	
	public City findById(Long id) {
		City city =  repository.findById(id).orElseThrow(() ->
				new ResponseStatusException(
				          HttpStatus.NOT_FOUND, "City Not Found"));
		return city;
	}
	
	public City registerCity(City city) {
		String name = removeAccent(city.getName()).toUpperCase();
		List<City> cities = repository.findByName(name);
		if (cities.isEmpty()) {			
			City newCity = this.fromResponse(api.searchForecastCityName(city.getName()));
			repository.insert(newCity);
			return newCity;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City already register");
		}
	}
	
	public void deleteCity(Long id) {
		repository.deleteById(id);
	}
	
	
	public City fromDTO(CityDTO dto) {
		return new City(dto.getId(), dto.getName());
	}	
	
	private City fromResponse(String json) {
		JSONObject response = new JSONObject(json);
		String name = response.getJSONObject("city").getString("name").toUpperCase();
		Long id = response.getJSONObject("city").getLong("id");
		return new City(id, name);
	}
	
	private static String removeAccent(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}


}
