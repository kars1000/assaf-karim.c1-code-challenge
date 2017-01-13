package com.codechallenge.entity;

import java.time.LocalDateTime;

import com.codechallenge.dto.MeasurementDTO;

public class MeasurementEntity {

	private LocalDateTime timeStamp;

	private Float temperature;
	private Float dewPoint;
	private Float precipitation;
	private String notes;

	public MeasurementEntity(MeasurementDTO dto) {
		this.timeStamp = dto.getTimeStamp();
		this.dewPoint = dto.getDewPoint();
		this.precipitation = dto.getPrecipitation();
		this.notes = dto.getNotes();
		this.temperature = dto.getTemperature();
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	public Float getDewPoint() {
		return dewPoint;
	}

	public void setDewPoint(Float dewPoint) {
		this.dewPoint = dewPoint;
	}

	public Float getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(Float precipitation) {
		this.precipitation = precipitation;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void populateNewValues(MeasurementDTO dto) {
		if (dto.getDewPoint() != null) {
			this.setDewPoint(dto.getDewPoint());
		}

		if (dto.getNotes() != null) {
			this.setNotes(dto.getNotes());
		}

		if (dto.getPrecipitation() != null) {
			this.setPrecipitation(dto.getPrecipitation());
		}

		if (dto.getTemperature() != null) {
			this.setTemperature(dto.getTemperature());
		}
	}

}
