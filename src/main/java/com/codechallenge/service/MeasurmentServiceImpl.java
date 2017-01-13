package com.codechallenge.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codechallenge.dto.MeasurementDTO;
import com.codechallenge.entity.MeasurementEntity;
import com.codechallenge.exceptions.TimeStampsDontMatchException;
import com.codechallenge.repository.MeasurementRepository;

@Service
public class MeasurmentServiceImpl implements MeasurementService {

	private MeasurementRepository measurementRepository;

	@Autowired
	public MeasurmentServiceImpl(MeasurementRepository measurementRepository) {
		this.measurementRepository = measurementRepository;

	}

	@Override
	public void addMeasurement(MeasurementDTO measurement) {

		measurementRepository.addMeasument(measurement);

	}

	@Override
	public List<MeasurementDTO> getAllMeasurements() {

		List<MeasurementEntity> entities = measurementRepository.getAllMeasurements();

		return entities.stream().map(e -> new MeasurementDTO(e)).collect(Collectors.toList());
	}

	@Override
	public MeasurementDTO getMeasurementByDatAndTime(LocalDateTime timeStamp) {

		MeasurementEntity entity = measurementRepository.findById(timeStamp);
		MeasurementDTO dto = null;

		if (entity != null) {
			dto = new MeasurementDTO(entity);
		}

		return dto;
	}

	@Override
	public List<MeasurementDTO> getMeasurementsFromSingleDay(LocalDate date) {

		List<MeasurementDTO> all = measurementRepository.getAllMeasurements().stream().map(e -> new MeasurementDTO(e))
				.collect(Collectors.toList());

		List<MeasurementDTO> results = all.stream().filter(e -> date.equals(e.getTimeStamp().toLocalDate()))
				.collect(Collectors.toList());

		return results;
	}

	@Override
	public MeasurementDTO updateMeasurment(MeasurementDTO dto, LocalDateTime dateTime) {
		MeasurementEntity entity = measurementRepository.findById(dateTime);

		MeasurementDTO updatedDto = null;

		if (entity != null) {

			if (!entity.getTimeStamp().equals(dto.getTimeStamp())) {
				throw new TimeStampsDontMatchException();
			}

			entity.populateNewValues(dto);

			updatedDto = new MeasurementDTO(entity);
		}

		return updatedDto;
	}

	@Override
	public void deleteMeasurement(LocalDateTime timeStamp) {
		measurementRepository.deleteMeasurement(timeStamp);

	}

}
