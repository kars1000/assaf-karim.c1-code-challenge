package com.codechallenge.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.codechallenge.dto.MeasurementDTO;

public interface MeasurementService {

	/**
	 * 
	 * @param measurement
	 */
	public void addMeasurement(MeasurementDTO measurement);

	/**
	 * 
	 * @return
	 */
	public List<MeasurementDTO> getAllMeasurements();

	/**
	 * 
	 * @param timeStamp
	 * @return
	 */
	public MeasurementDTO getMeasurementByDatAndTime(LocalDateTime timeStamp);

	/**
	 * 
	 * @param date
	 * @return
	 */
	public List<MeasurementDTO> getMeasurementsFromSingleDay(LocalDate date);

	/**
	 * 
	 * @param dateTime
	 * @param date
	 * @return
	 */
	public MeasurementDTO updateMeasurment(MeasurementDTO dto, LocalDateTime dateTime);

	/**
	 * 
	 * @param timeStamp
	 */
	public void deleteMeasurement(LocalDateTime timeStamp);

}
