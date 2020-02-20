package com.weather.forecast.consumer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
public class OpenWheatherApiComponent {
	
	private static final String URLAPINAME = "https://api.openweathermap.org/data/2.5/forecast/daily?q={city}&cnt={cnt}&units=metric&appid={apikey}";

	private static final String URLAPIID = "https://api.openweathermap.org/data/2.5/forecast/daily?id={id}&cnt={cnt}&units=metric&appid={apikey}";
		
	private static final String APIKEY = "eb8b1a9405e659b2ffc78f0a520b1a46";
	
	private static final Integer DAYSFORECAST = 5;
	
	public String searchForecastCityName(String name) {
		RestTemplate restTemplate = new RestTemplate();
		String json = "";
		try {
			json = restTemplate.getForObject(URLAPINAME, String.class, name, DAYSFORECAST, APIKEY);
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"); 
			}
		}
		return json;
	}
	
	public String searchForecastCityId(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		String json = "";
		try {		
			json = restTemplate.getForObject(URLAPIID, String.class, id, DAYSFORECAST, APIKEY);
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"); 
			}
		}
		return json;	
	}	
}
