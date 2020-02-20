package com.weather.forecast.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weather.forecast.consumer.OpenWheatherApiComponent;
import com.weather.forecast.domains.Forecast;

@Service
public class ForecastService {
	
	@Autowired
	private OpenWheatherApiComponent api;
	
	public List<Forecast> getForecast(Long id) {
		return this.fromResponse(api.searchForecastCityId(id));		
	}
	
	private List<Forecast> fromResponse(String json) {
		List<Forecast> list = new ArrayList<>();
		JSONObject response = new JSONObject(json);
		String cityName = response.getJSONObject("city").getString("name").toUpperCase();
		JSONArray forecastList = response.getJSONArray("list");
		forecastList.forEach(x -> {
			JSONObject obj = (JSONObject) x;
			list.add(fromJSONObject(cityName, obj));
		});
		return list;
	}
	
	private Forecast fromJSONObject(String cityName, JSONObject obj) {
		Forecast item = new Forecast();
		item.setCityName(cityName);
		Instant instant = Instant.ofEpochSecond(obj.getLong("dt"));
		item.setDate(Date.from(instant));
		JSONObject temp = obj.getJSONObject("temp");
		item.setTempDay(temp.getDouble("day"));
		item.setTempEve(temp.getDouble("eve"));
		item.setTempMax(temp.getDouble("max"));
		item.setTempMin(temp.getDouble("min"));
		item.setTempMorn(temp.getDouble("morn"));
		item.setTempNight(temp.getDouble("night"));
		return item;
	}

}
