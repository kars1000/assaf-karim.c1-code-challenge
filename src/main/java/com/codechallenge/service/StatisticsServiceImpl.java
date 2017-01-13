package com.codechallenge.service;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codechallenge.dto.MeasurementDTO;
import com.codechallenge.dto.StatisticsDTO;
import com.codechallenge.repository.MeasurementRepository;
import com.codechallenge.strategy.StatisticsStrategy;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	private MeasurementRepository measurementRepository;

	private Map<String, StatisticsStrategy> stratagies;

	@Autowired
	public StatisticsServiceImpl(MeasurementRepository measurementRepository,
			Map<String, StatisticsStrategy> stratagies) {
		this.measurementRepository = measurementRepository;
		this.stratagies = stratagies;
	}

	@Override
	public List<StatisticsDTO> findStats(StatisticsDTO dto) {

		List<MeasurementDTO> MeasurementDTOs = measurementRepository
				.findMeasuremtsBetweenDates(dto.getFromDateTime(), dto.getToDateTime()).stream().map(e -> new MeasurementDTO(e))
				.collect(Collectors.toList());

		List<StatisticsDTO> resultsDTO = new ArrayList<>();


		for (String metric : dto.getMetric()) {

			for (String stat : dto.getStat()) {
				StatisticsDTO result = stratagies.get(stat).calculate(MeasurementDTOs, metric);

				if (result != null) {
					result.setVal(metric);
					resultsDTO.add(result);
				}

			}

		}

		return resultsDTO;
	}

}
