package com.codechallenge.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.codechallenge.dto.MeasurementDTO;
import com.codechallenge.entity.MeasurementEntity;

@Repository
public class MeasurementRepositoryImpl implements MeasurementRepository {

	/**
	 * In memory DB.
	 */
	private static Map<LocalDateTime, MeasurementEntity> db = new HashMap<>();

	public void addMeasument(MeasurementDTO dto) {

		db.put(dto.getTimeStamp(), new MeasurementEntity(dto));

	}

	@Override
	public List<MeasurementEntity> getAllMeasurements() {

		return new ArrayList<MeasurementEntity>(db.values());
	}

	@Override
	public MeasurementEntity findById(LocalDateTime id) {

		return db.get(id);
	}

	@Override
	public void deleteMeasurement(LocalDateTime id) {

		db.remove(id);

	}

	@Override
	public List<MeasurementEntity> findMeasuremtsBetweenDates(LocalDateTime start, LocalDateTime end) {

		return db.values().stream()
				.filter(e -> !e.getTimeStamp().isBefore(start)
						&& (!e.getTimeStamp().isAfter(end) && !e.getTimeStamp().equals(end)))
				.collect(Collectors.toList());
	}

}
