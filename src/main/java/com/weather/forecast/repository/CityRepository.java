package com.weather.forecast.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.weather.forecast.domains.City;

@Repository
public interface CityRepository extends MongoRepository<City, Long> {
	
	List<City> findByName(String name);

}
