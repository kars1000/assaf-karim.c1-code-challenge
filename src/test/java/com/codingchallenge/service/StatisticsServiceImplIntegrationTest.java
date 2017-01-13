package com.codingchallenge.service;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.codechallenge.config.Application;
import com.codechallenge.dto.MeasurementDTO;
import com.codechallenge.dto.StatisticsDTO;
import com.codechallenge.service.MeasurementService;
import com.codechallenge.service.StatisticsService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { Application.class })
public class StatisticsServiceImplIntegrationTest {

	@Autowired
	private MeasurementService measurementService;

	@Autowired
	private StatisticsService statisticsService;
	
	
	
	@Test
	public void findStats_usingDewPointAsMatricsAndOneRecord_maxValueReturned() {

		LocalDateTime date1 = LocalDateTime.parse("2015-09-01T16:00:00.000");
		LocalDateTime date2 = LocalDateTime.parse("2015-09-01T16:10:00.000");
		LocalDateTime date3 = LocalDateTime.parse("2015-09-01T16:20:00.000");
		LocalDateTime date4 = LocalDateTime.parse("2015-09-01T16:30:00.000");
		LocalDateTime date5 = LocalDateTime.parse("2015-09-01T16:40:00.000");
		LocalDateTime date6 = LocalDateTime.parse("2015-09-01T17:00:00.000");

		MeasurementDTO dto1 = new MeasurementDTO(date1);
		dto1.setTemperature(27.1f);
		dto1.setDewPoint(16.9f);

		MeasurementDTO dto2 = new MeasurementDTO(date2);

		dto2.setTemperature(27.3f);


		MeasurementDTO dto3 = new MeasurementDTO(date3);

		dto3.setTemperature(27.5f);
		dto3.setDewPoint(17.1f);

		MeasurementDTO dto4 = new MeasurementDTO(date4);

		dto4.setTemperature(27.4f);
		dto4.setDewPoint(17.3f);

		MeasurementDTO dto5 = new MeasurementDTO(date5);

		dto5.setTemperature(27.2f);


		MeasurementDTO dto6 = new MeasurementDTO(date6);

		dto6.setTemperature(28.1f);
		dto6.setDewPoint(18.3f);

		measurementService.addMeasurement(dto1);
		measurementService.addMeasurement(dto2);
		measurementService.addMeasurement(dto3);
		measurementService.addMeasurement(dto4);
		measurementService.addMeasurement(dto5);
		measurementService.addMeasurement(dto6);

		StatisticsDTO dto = new StatisticsDTO();

		List<String> stats = new ArrayList<>();
		stats.add("max");

		List<String> metrics = new ArrayList<>();
		metrics.add("dewPoint");

		dto.setMetric(metrics);
		dto.setStat(stats);

		dto.setFromDateTime(LocalDateTime.parse("2015-09-01T16:00:00.000"));
		dto.setToDateTime(LocalDateTime.parse("2015-09-01T16:05:00.000"));

		List<StatisticsDTO> result = statisticsService.findStats(dto);

		assertEquals("16.9", result.get(0).getMax());

	}
	

	@Test
	public void findStats_usingDewPointAsMatrics_maxValueReturened() {

		LocalDateTime date1 = LocalDateTime.parse("2015-09-01T16:00:00.000");
		LocalDateTime date2 = LocalDateTime.parse("2015-09-01T16:10:00.000");
		LocalDateTime date3 = LocalDateTime.parse("2015-09-01T16:20:00.000");
		LocalDateTime date4 = LocalDateTime.parse("2015-09-01T16:30:00.000");
		LocalDateTime date5 = LocalDateTime.parse("2015-09-01T16:40:00.000");
		LocalDateTime date6 = LocalDateTime.parse("2015-09-01T17:00:00.000");

		MeasurementDTO dto1 = new MeasurementDTO(date1);
		dto1.setTemperature(27.1f);
		dto1.setDewPoint(16.9f);

		MeasurementDTO dto2 = new MeasurementDTO(date2);

		dto2.setTemperature(27.3f);


		MeasurementDTO dto3 = new MeasurementDTO(date3);

		dto3.setTemperature(27.5f);
		dto3.setDewPoint(17.1f);

		MeasurementDTO dto4 = new MeasurementDTO(date4);

		dto4.setTemperature(27.4f);
		dto4.setDewPoint(17.3f);

		MeasurementDTO dto5 = new MeasurementDTO(date5);

		dto5.setTemperature(27.2f);


		MeasurementDTO dto6 = new MeasurementDTO(date6);

		dto6.setTemperature(28.1f);
		dto6.setDewPoint(18.3f);

		measurementService.addMeasurement(dto1);
		measurementService.addMeasurement(dto2);
		measurementService.addMeasurement(dto3);
		measurementService.addMeasurement(dto4);
		measurementService.addMeasurement(dto5);
		measurementService.addMeasurement(dto6);

		StatisticsDTO dto = new StatisticsDTO();

		List<String> stats = new ArrayList<>();
		stats.add("max");

		List<String> metrics = new ArrayList<>();
		metrics.add("dewPoint");

		dto.setMetric(metrics);
		dto.setStat(stats);

		dto.setFromDateTime(LocalDateTime.parse("2015-09-01T16:00:00.000"));
		dto.setToDateTime(LocalDateTime.parse("2015-09-01T17:00:00.000"));

		List<StatisticsDTO> result = statisticsService.findStats(dto);

		assertEquals("17.3", result.get(0).getMax());

	}

	@Test
	public void findStats_usingTemperatureAsMatrics_maxValueReturened()
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		LocalDateTime date1 = LocalDateTime.parse("2015-09-01T16:00:00.000");
		LocalDateTime date2 = LocalDateTime.parse("2015-09-01T16:10:00.000");
		LocalDateTime date3 = LocalDateTime.parse("2015-09-01T16:20:00.000");
		LocalDateTime date4 = LocalDateTime.parse("2015-09-01T16:30:00.000");
		LocalDateTime date5 = LocalDateTime.parse("2015-09-01T16:40:00.000");
		LocalDateTime date6 = LocalDateTime.parse("2015-09-01T17:00:00.000");

		MeasurementDTO dto1 = new MeasurementDTO(date1);
		dto1.setTemperature(27.1f);
		dto1.setDewPoint(100f);

		MeasurementDTO dto2 = new MeasurementDTO(date2);

		dto2.setTemperature(27.3f);
		dto2.setDewPoint(100f);

		MeasurementDTO dto3 = new MeasurementDTO(date3);

		dto3.setTemperature(27.5f);
		dto3.setDewPoint(100f);

		MeasurementDTO dto4 = new MeasurementDTO(date4);

		dto4.setTemperature(27.4f);
		dto4.setDewPoint(100f);

		MeasurementDTO dto5 = new MeasurementDTO(date5);

		dto5.setTemperature(27.2f);
		dto5.setDewPoint(100f);

		MeasurementDTO dto6 = new MeasurementDTO(date6);

		dto6.setTemperature(28.1f);
		dto6.setDewPoint(100f);

		measurementService.addMeasurement(dto1);
		measurementService.addMeasurement(dto2);
		measurementService.addMeasurement(dto3);
		measurementService.addMeasurement(dto4);
		measurementService.addMeasurement(dto5);
		measurementService.addMeasurement(dto6);

		StatisticsDTO dto = new StatisticsDTO();

		List<String> stats = new ArrayList<>();
		stats.add("max");

		List<String> metrics = new ArrayList<>();
		metrics.add("temperature");

		dto.setMetric(metrics);
		dto.setStat(stats);

		dto.setFromDateTime(LocalDateTime.parse("2015-09-01T16:00:00.000"));
		dto.setToDateTime(LocalDateTime.parse("2015-09-01T17:00:00.000"));

		List<StatisticsDTO> result = statisticsService.findStats(dto);

		assertEquals(result.get(0).getMax(), "27.5");

	}

	@Test
	public void findStats_usingTemperatureAsMatrics_minValueReturened() {

		LocalDateTime date1 = LocalDateTime.parse("2015-09-01T16:00:00.000");
		LocalDateTime date2 = LocalDateTime.parse("2015-09-01T16:10:00.000");
		LocalDateTime date3 = LocalDateTime.parse("2015-09-01T16:20:00.000");
		LocalDateTime date4 = LocalDateTime.parse("2015-09-01T16:30:00.000");
		LocalDateTime date5 = LocalDateTime.parse("2015-09-01T16:40:00.000");
		LocalDateTime date6 = LocalDateTime.parse("2015-09-01T17:00:00.000");

		MeasurementDTO dto1 = new MeasurementDTO(date1);
		dto1.setTemperature(27.1f);
		dto1.setDewPoint(100f);

		MeasurementDTO dto2 = new MeasurementDTO(date2);

		dto2.setTemperature(27.3f);
		dto2.setDewPoint(100f);

		MeasurementDTO dto3 = new MeasurementDTO(date3);

		dto3.setTemperature(27.5f);
		dto3.setDewPoint(100f);

		MeasurementDTO dto4 = new MeasurementDTO(date4);

		dto4.setTemperature(27.4f);
		dto4.setDewPoint(100f);

		MeasurementDTO dto5 = new MeasurementDTO(date5);

		dto5.setTemperature(27.2f);
		dto5.setDewPoint(100f);

		MeasurementDTO dto6 = new MeasurementDTO(date6);

		dto6.setTemperature(28.1f);
		dto6.setDewPoint(100f);

		measurementService.addMeasurement(dto1);
		measurementService.addMeasurement(dto2);
		measurementService.addMeasurement(dto3);
		measurementService.addMeasurement(dto4);
		measurementService.addMeasurement(dto5);
		measurementService.addMeasurement(dto6);

		StatisticsDTO dto = new StatisticsDTO();

		List<String> stats = new ArrayList<>();
		stats.add("min");

		List<String> metrics = new ArrayList<>();
		metrics.add("temperature");

		dto.setMetric(metrics);
		dto.setStat(stats);

		dto.setFromDateTime(LocalDateTime.parse("2015-09-01T16:00:00.000"));
		dto.setToDateTime(LocalDateTime.parse("2015-09-01T17:00:00.000"));

		List<StatisticsDTO> result = statisticsService.findStats(dto);

		assertEquals(result.get(0).getMin(), "27.1");

	}

	@Test
	public void findStats_usingTemperatureAsMatrics_averageValueReturned() {

		LocalDateTime date1 = LocalDateTime.parse("2015-09-01T16:00:00.000");
		LocalDateTime date2 = LocalDateTime.parse("2015-09-01T16:10:00.000");
		LocalDateTime date3 = LocalDateTime.parse("2015-09-01T16:20:00.000");
		LocalDateTime date4 = LocalDateTime.parse("2015-09-01T16:30:00.000");
		LocalDateTime date5 = LocalDateTime.parse("2015-09-01T16:40:00.000");
		LocalDateTime date6 = LocalDateTime.parse("2015-09-01T17:00:00.000");

		MeasurementDTO dto1 = new MeasurementDTO(date1);
		dto1.setTemperature(27.1f);
		dto1.setDewPoint(100f);

		MeasurementDTO dto2 = new MeasurementDTO(date2);

		dto2.setTemperature(27.3f);
		dto2.setDewPoint(100f);

		MeasurementDTO dto3 = new MeasurementDTO(date3);

		dto3.setTemperature(27.5f);
		dto3.setDewPoint(100f);

		MeasurementDTO dto4 = new MeasurementDTO(date4);

		dto4.setTemperature(27.4f);
		dto4.setDewPoint(100f);

		MeasurementDTO dto5 = new MeasurementDTO(date5);

		dto5.setTemperature(27.2f);
		dto5.setDewPoint(100f);

		MeasurementDTO dto6 = new MeasurementDTO(date6);

		dto6.setTemperature(28.1f);
		dto6.setDewPoint(100f);

		measurementService.addMeasurement(dto1);
		measurementService.addMeasurement(dto2);
		measurementService.addMeasurement(dto3);
		measurementService.addMeasurement(dto4);
		measurementService.addMeasurement(dto5);
		measurementService.addMeasurement(dto6);

		StatisticsDTO dto = new StatisticsDTO();

		List<String> stats = new ArrayList<>();
		stats.add("average");

		List<String> metrics = new ArrayList<>();
		metrics.add("temperature");

		dto.setMetric(metrics);
		dto.setStat(stats);

		dto.setFromDateTime(LocalDateTime.parse("2015-09-01T16:00:00.000"));
		dto.setToDateTime(LocalDateTime.parse("2015-09-01T17:00:00.000"));

		List<StatisticsDTO> result = statisticsService.findStats(dto);

		assertEquals(result.get(0).getAverage(), "27.3");

	}
	
	@Test
	public void findStats_usingMetricsThatsNeverBeenReported_returnEmptyArray() {

		LocalDateTime date1 = LocalDateTime.parse("2015-09-01T16:00:00.000");
		LocalDateTime date2 = LocalDateTime.parse("2015-09-01T16:10:00.000");
		LocalDateTime date3 = LocalDateTime.parse("2015-09-01T16:20:00.000");
		LocalDateTime date4 = LocalDateTime.parse("2015-09-01T16:30:00.000");
		LocalDateTime date5 = LocalDateTime.parse("2015-09-01T16:40:00.000");
		LocalDateTime date6 = LocalDateTime.parse("2015-09-01T17:00:00.000");

		MeasurementDTO dto1 = new MeasurementDTO(date1);
		dto1.setTemperature(27.1f);
		dto1.setDewPoint(100f);

		MeasurementDTO dto2 = new MeasurementDTO(date2);

		dto2.setTemperature(27.3f);
		dto2.setDewPoint(100f);

		MeasurementDTO dto3 = new MeasurementDTO(date3);

		dto3.setTemperature(27.5f);
		dto3.setDewPoint(100f);

		MeasurementDTO dto4 = new MeasurementDTO(date4);

		dto4.setTemperature(27.4f);
		dto4.setDewPoint(100f);

		MeasurementDTO dto5 = new MeasurementDTO(date5);

		dto5.setTemperature(27.2f);
		dto5.setDewPoint(100f);

		MeasurementDTO dto6 = new MeasurementDTO(date6);

		dto6.setTemperature(28.1f);
		dto6.setDewPoint(100f);

		measurementService.addMeasurement(dto1);
		measurementService.addMeasurement(dto2);
		measurementService.addMeasurement(dto3);
		measurementService.addMeasurement(dto4);
		measurementService.addMeasurement(dto5);
		measurementService.addMeasurement(dto6);

		StatisticsDTO dto = new StatisticsDTO();

		List<String> stats = new ArrayList<>();
		stats.add("average");

		List<String> metrics = new ArrayList<>();
		metrics.add("precipitation");

		dto.setMetric(metrics);
		dto.setStat(stats);

		dto.setFromDateTime(LocalDateTime.parse("2015-09-01T16:00:00.000"));
		dto.setToDateTime(LocalDateTime.parse("2015-09-01T17:00:00.000"));

		List<StatisticsDTO> result = statisticsService.findStats(dto);

		assertEquals(0,result.size());

	}
	
	
	@Test
	public void findStats_statsForMultipleMetrics_returnStatsForTwoMetrics() {

		LocalDateTime date1 = LocalDateTime.parse("2015-09-01T16:00:00.000");
		LocalDateTime date2 = LocalDateTime.parse("2015-09-01T16:10:00.000");
		LocalDateTime date3 = LocalDateTime.parse("2015-09-01T16:20:00.000");
		LocalDateTime date4 = LocalDateTime.parse("2015-09-01T16:30:00.000");
		LocalDateTime date5 = LocalDateTime.parse("2015-09-01T16:40:00.000");
		LocalDateTime date6 = LocalDateTime.parse("2015-09-01T17:00:00.000");

		MeasurementDTO dto1 = new MeasurementDTO(date1);
		dto1.setTemperature(27.1f);
		dto1.setDewPoint(100f);

		MeasurementDTO dto2 = new MeasurementDTO(date2);

		dto2.setTemperature(27.3f);
		dto2.setDewPoint(100f);

		MeasurementDTO dto3 = new MeasurementDTO(date3);

		dto3.setTemperature(27.5f);
		dto3.setDewPoint(100f);

		MeasurementDTO dto4 = new MeasurementDTO(date4);

		dto4.setTemperature(27.4f);
		dto4.setDewPoint(100f);

		MeasurementDTO dto5 = new MeasurementDTO(date5);

		dto5.setTemperature(27.2f);
		dto5.setDewPoint(100f);

		MeasurementDTO dto6 = new MeasurementDTO(date6);

		dto6.setTemperature(28.1f);
		dto6.setDewPoint(100f);

		measurementService.addMeasurement(dto1);
		measurementService.addMeasurement(dto2);
		measurementService.addMeasurement(dto3);
		measurementService.addMeasurement(dto4);
		measurementService.addMeasurement(dto5);
		measurementService.addMeasurement(dto6);

		StatisticsDTO dto = new StatisticsDTO();

		List<String> stats = new ArrayList<>();
		stats.add("average");
		stats.add("min");
		stats.add("max");

		List<String> metrics = new ArrayList<>();
		metrics.add("temperature");
		metrics.add("dewPoint");

		dto.setMetric(metrics);
		dto.setStat(stats);

		dto.setFromDateTime(LocalDateTime.parse("2015-09-01T16:00:00.000"));
		dto.setToDateTime(LocalDateTime.parse("2015-09-01T17:00:00.000"));

		List<StatisticsDTO> result = statisticsService.findStats(dto);
		
		
		result.forEach(System.out::println);

		assertEquals(6,result.size());

	}

}
