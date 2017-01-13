package com.codingchallenge.service;

import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.codechallenge.config.Application;
import com.codechallenge.dto.MeasurementDTO;
import com.codechallenge.service.MeasurementService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { Application.class })
public class MeasurementServiceImplIntegrationTest {

	@Autowired
	private MeasurementService measurementService;

	@Before
	public void setUp() {

		measurementService.getAllMeasurements().clear();

		List<MeasurementDTO> t = measurementService.getAllMeasurements();

		System.out.println(t.size());

	}

	@Test
	public void addMeasurement_addRecordToDB_recordAddedSuccessfully() {

		MeasurementDTO dto = new MeasurementDTO(LocalDateTime.now());

		dto.setNotes("TESTING");

		measurementService.addMeasurement(dto);

		assertTrue(!measurementService.getAllMeasurements().isEmpty());

	}

	@Test
	public void getMeasurementByDatAndTime_recordFound_singleRecordReturned() {

		LocalDateTime date1 = LocalDateTime.parse("2015-09-01T16:00:00.000");

		LocalDateTime date2 = LocalDateTime.parse("2015-09-01T16:10:00.000");
		LocalDateTime date3 = LocalDateTime.parse("2015-09-01T16:20:00.000");
		LocalDateTime date4 = LocalDateTime.parse("2015-09-01T16:30:00.000");
		LocalDateTime date5 = LocalDateTime.parse("2015-09-01T16:40:00.000");
		LocalDateTime date6 = LocalDateTime.parse("2015-09-02T16:00:00.000");

		MeasurementDTO dto1 = new MeasurementDTO(date1);
		dto1.setTemperature(120f);

		MeasurementDTO dto2 = new MeasurementDTO(date2);
		dto2.setTemperature(10f);
		dto2.setDewPoint(10f);
		MeasurementDTO dto3 = new MeasurementDTO(date3);
		MeasurementDTO dto4 = new MeasurementDTO(date4);
		MeasurementDTO dto5 = new MeasurementDTO(date5);
		MeasurementDTO dto6 = new MeasurementDTO(date6);

		measurementService.addMeasurement(dto1);
		measurementService.addMeasurement(dto2);
		measurementService.addMeasurement(dto3);
		measurementService.addMeasurement(dto4);
		measurementService.addMeasurement(dto5);
		measurementService.addMeasurement(dto6);

		MeasurementDTO result = measurementService.getMeasurementByDatAndTime(date2);

		assertEquals(10f, result.getDewPoint(), 10f - result.getDewPoint());

	}

	@Test
	public void getMeasurementByDatAndTime_recordNotFound_noValueReturned() {

		LocalDateTime date1 = LocalDateTime.parse("2017-09-01T16:00:00.000");
		LocalDateTime date2 = LocalDateTime.parse("2017-09-01T16:10:00.000");
		LocalDateTime date3 = LocalDateTime.parse("2017-09-01T16:20:00.000");
		LocalDateTime date4 = LocalDateTime.parse("2017-09-01T16:30:00.000");
		LocalDateTime date5 = LocalDateTime.parse("2017-09-01T16:40:00.000");
		LocalDateTime date6 = LocalDateTime.parse("2017-09-02T16:00:00.000");

		MeasurementDTO dto1 = new MeasurementDTO(date1);
		dto1.setTemperature(120f);
		MeasurementDTO dto2 = new MeasurementDTO(date2);
		dto2.setTemperature(10f);
		MeasurementDTO dto3 = new MeasurementDTO(date3);
		MeasurementDTO dto4 = new MeasurementDTO(date4);
		MeasurementDTO dto5 = new MeasurementDTO(date5);
		MeasurementDTO dto6 = new MeasurementDTO(date6);

		measurementService.addMeasurement(dto1);
		measurementService.addMeasurement(dto2);
		measurementService.addMeasurement(dto3);
		measurementService.addMeasurement(dto4);
		measurementService.addMeasurement(dto5);

		MeasurementDTO result = measurementService.getMeasurementByDatAndTime(date6);

		assertTrue(result == null);

	}

	@Test
	public void getMeasurementsFromSingleDay_singleDaysRecordsRetrieved_twoRecordsReturned() {

		LocalDateTime date1 = LocalDateTime.parse("2018-09-05T16:00:00.000");
		LocalDateTime date2 = LocalDateTime.parse("2018-09-01T16:10:00.000");
		LocalDateTime date3 = LocalDateTime.parse("2018-09-01T16:20:00.000");
		LocalDateTime date4 = LocalDateTime.parse("2018-09-01T16:30:00.000");
		LocalDateTime date5 = LocalDateTime.parse("2018-09-01T16:40:00.000");
		LocalDateTime date6 = LocalDateTime.parse("2018-09-05T16:20:00.000");

		MeasurementDTO dto1 = new MeasurementDTO(date1);

		MeasurementDTO dto2 = new MeasurementDTO(date2);

		MeasurementDTO dto3 = new MeasurementDTO(date3);
		MeasurementDTO dto4 = new MeasurementDTO(date4);
		MeasurementDTO dto5 = new MeasurementDTO(date5);
		MeasurementDTO dto6 = new MeasurementDTO(date6);

		measurementService.addMeasurement(dto1);
		measurementService.addMeasurement(dto2);
		measurementService.addMeasurement(dto3);
		measurementService.addMeasurement(dto4);
		measurementService.addMeasurement(dto5);
		measurementService.addMeasurement(dto6);

		List<MeasurementDTO> result = measurementService.getMeasurementsFromSingleDay(date6.toLocalDate());

		assertTrue(result.size() == 2);

	}

	@Test
	public void updateMeasurment_recordExsits_recordUpdatedWithNewValues() {

		LocalDateTime date1 = LocalDateTime.parse("2019-09-01T16:00:00.000");
		LocalDateTime date2 = LocalDateTime.parse("2019-09-01T16:10:00.000");

		MeasurementDTO dto1 = new MeasurementDTO(date1);
		dto1.setDewPoint(100f);

		MeasurementDTO dto2 = new MeasurementDTO(date2);

		measurementService.addMeasurement(dto1);
		measurementService.addMeasurement(dto2);

		MeasurementDTO updated = new MeasurementDTO(date1);
		updated.setDewPoint(200f);

		MeasurementDTO result = measurementService.updateMeasurment(updated, date1);

		assertEquals(200f, result.getDewPoint(), 200f - result.getDewPoint());

	}

	@Test
	public void updateMeasurment_timeStampsDontMatch_throwTimeStampsDontMatchException() {

		LocalDateTime date1 = LocalDateTime.parse("2019-09-01T16:00:00.000");
		LocalDateTime date2 = LocalDateTime.parse("2019-09-01T16:10:00.000");

		MeasurementDTO dto1 = new MeasurementDTO(date1);
		dto1.setDewPoint(100f);

		MeasurementDTO dto2 = new MeasurementDTO(date2);

		measurementService.addMeasurement(dto1);
		measurementService.addMeasurement(dto2);

		MeasurementDTO updated = new MeasurementDTO(date2);
		updated.setDewPoint(200f);

		try {

			MeasurementDTO result = measurementService.updateMeasurment(updated, date1);

			fail("Should never reach this block of code");

		} catch (Exception e) {

		}

	}

	@Test
	public void updateMeasurment_measurementDoesntExist_returnNullValue() {

		LocalDateTime date1 = LocalDateTime.parse("2019-09-01T16:00:00.000");
		LocalDateTime date2 = LocalDateTime.parse("2019-09-01T16:10:00.000");

		MeasurementDTO dto1 = new MeasurementDTO(date1);
		dto1.setDewPoint(100f);

		MeasurementDTO dto2 = new MeasurementDTO(date2);

		// measurementService.addMeasurement(dto1);
		measurementService.addMeasurement(dto2);

		MeasurementDTO updated = new MeasurementDTO(date1);
		updated.setDewPoint(200f);

		MeasurementDTO result = measurementService.updateMeasurment(dto1, LocalDateTime.now());

		assertTrue(result == null);

	}

	@Test
	public void deleteMeasurementt_measurementExists_measurementDeletedSuccessfully() {

		LocalDateTime date1 = LocalDateTime.parse("2020-09-01T16:00:00.000");

		MeasurementDTO dto1 = new MeasurementDTO(date1);

		measurementService.addMeasurement(dto1);

		MeasurementDTO available = measurementService.getMeasurementByDatAndTime(dto1.getTimeStamp());

		assertTrue(available != null);

		measurementService.deleteMeasurement(dto1.getTimeStamp());

		assertTrue(measurementService.getMeasurementByDatAndTime(dto1.getTimeStamp()) == null);

	}

	@Test
	public void deleteMeasurementt_measurementDoesntExist_measurementIsNull() {

		measurementService.deleteMeasurement(LocalDateTime.now());

		assertTrue(measurementService.getMeasurementByDatAndTime(LocalDateTime.now()) == null);

	}

}
