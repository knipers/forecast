package com.weather.forecast.domains;

import java.util.Date;

public class Forecast {
	
	private String cityName;
	private java.util.Date date;
	private Double tempDay;
	private Double tempNight;
	private Double tempMax;
	private Double tempMin;
	private Double tempEve;
	private Double tempMorn;

	public Forecast(String cityName, Date date, Double tempDay, Double tempNight, Double tempMax, Double tempMin,
			Double tempEve, Double tempMorn) {
		this.cityName = cityName;
		this.date = date;
		this.tempDay = tempDay;
		this.tempNight = tempNight;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
		this.tempEve = tempEve;
		this.tempMorn = tempMorn;
	}
	
	public Forecast() {
		
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public Double getTempDay() {
		return tempDay;
	}

	public void setTempDay(Double tempDay) {
		this.tempDay = tempDay;
	}

	public Double getTempNight() {
		return tempNight;
	}

	public void setTempNight(Double tempNight) {
		this.tempNight = tempNight;
	}

	public Double getTempMax() {
		return tempMax;
	}

	public void setTempMax(Double tempMax) {
		this.tempMax = tempMax;
	}

	public Double getTempMin() {
		return tempMin;
	}

	public void setTempMin(Double tempMin) {
		this.tempMin = tempMin;
	}

	public Double getTempEve() {
		return tempEve;
	}

	public void setTempEve(Double tempEve) {
		this.tempEve = tempEve;
	}

	public Double getTempMorn() {
		return tempMorn;
	}

	public void setTempMorn(Double tempMorn) {
		this.tempMorn = tempMorn;
	}

	@Override
	public String toString() {
		return "ForecastDTO [cityName=" + cityName + ", date=" + date + ", tempDay=" + tempDay + ", tempNight="
				+ tempNight + ", tempMax=" + tempMax + ", tempMin=" + tempMin + ", tempEve=" + tempEve + ", tempMorn="
				+ tempMorn + "]";
	}
}
