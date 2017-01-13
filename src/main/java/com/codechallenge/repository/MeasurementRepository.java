package com.codechallenge.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.codechallenge.dto.MeasurementDTO;
import com.codechallenge.entity.MeasurementEntity;

public interface MeasurementRepository {

	/**
	 * 
	 * @param dto
	 */
	public void addMeasument(MeasurementDTO dto);

	/***
	 * 
	 * @return
	 */
	public List<MeasurementEntity> getAllMeasurements();

	/**
	 * 
	 * @param id
	 * @return
	 */
	public MeasurementEntity findById(LocalDateTime id);

	/**
	 * 
	 * @param id
	 */
	public void deleteMeasurement(LocalDateTime id);

	/**
	 * 
	 * @param id
	 * @param id
	 * @return
	 */
	public List<MeasurementEntity> findMeasuremtsBetweenDates(LocalDateTime start, LocalDateTime end);

}
