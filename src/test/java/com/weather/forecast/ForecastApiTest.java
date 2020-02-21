package com.weather.forecast;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;

import com.weather.forecast.consumer.OpenWheatherApiComponent;
import com.weather.forecast.domains.City;
import com.weather.forecast.domains.Forecast;
import com.weather.forecast.repository.CityRepository;
import com.weather.forecast.services.CityService;
import com.weather.forecast.services.ForecastService;

@RunWith(MockitoJUnitRunner.class)
public class ForecastApiTest {
	
    @InjectMocks
    CityService cityService;
    
    @InjectMocks
    ForecastService forecastService;
    
    @Mock
    CityRepository repository;
    
    @Spy
    OpenWheatherApiComponent api;
    
	@Test
	public void testRegisterRealyCity() {
		City city = new City(1L, "Blumenau");
		List<City> list = new ArrayList<>();
		when(repository.findByName(any())).thenReturn(list);

		city = cityService.registerCity(city);
		assertNotNull(city);
	}
	
    
	@Test
	public void testRegisterFalseCity() {
		City city = new City(1L, "Coruscant");
		List<City> list = new ArrayList<>();
		when(repository.findByName(any())).thenReturn(list);
		try {
			city = cityService.registerCity(city);
		} catch(ResponseStatusException e) {
			if (e.getStatus().equals(HttpStatus.NOT_FOUND)) {
				city = null;
			}
		}
		assertNull(city);
	}
	
	@Test
	public void testGetRealyForecast() {
		Long id = Long.valueOf("3386370");
		List<Forecast> list = null;
		try {
			list = forecastService.getForecast(id);
		} catch(ResponseStatusException e) {
			if (e.getStatus().equals(HttpStatus.NOT_FOUND)) {
				list = null;
			}
		}
		assertNotNull(list);
	}
	
	@Test
	public void testGetFalseForecast() {
		Long id = Long.valueOf("999988888877777");
		List<Forecast> list = null;
		try {
			list = forecastService.getForecast(id);
		} catch(ResponseStatusException e) {
			if (e.getStatus().equals(HttpStatus.NOT_FOUND)) {
				list = null;
			}
		}
		assertNull(list);
	}
	

}
