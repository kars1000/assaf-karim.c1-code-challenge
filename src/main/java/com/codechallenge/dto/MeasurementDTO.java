package com.codechallenge.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.codechallenge.entity.MeasurementEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MeasurementDTO {

	@NotNull
	private LocalDateTime timeStamp;

	private Float temperature;
	private Float dewPoint;
	private Float precipitation;
	private String notes;

	public MeasurementDTO(LocalDateTime timeStamp) {
		super();
		this.timeStamp = timeStamp;
	}

	public MeasurementDTO() {

	}

	public MeasurementDTO(MeasurementEntity e) {
		this.timeStamp = e.getTimeStamp();
		this.dewPoint = e.getDewPoint();
		this.precipitation = e.getPrecipitation();
		this.notes = e.getNotes();
		this.temperature = e.getTemperature();

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

}
