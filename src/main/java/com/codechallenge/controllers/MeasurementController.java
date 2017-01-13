package com.codechallenge.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codechallenge.dto.MeasurementDTO;
import com.codechallenge.exceptions.TimeStampsDontMatchException;
import com.codechallenge.service.MeasurementService;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

	private MeasurementService measurementService;

	@Autowired
	public MeasurementController(MeasurementService measurementService) {
		this.measurementService = measurementService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addMeasurement(@RequestBody @Valid MeasurementDTO measurmentDTO)
			throws URISyntaxException {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(
				new URI("/measurements/" + measurmentDTO.getTimeStamp().format(DateTimeFormatter.ISO_DATE_TIME)));

		return new ResponseEntity<>("", responseHeaders, HttpStatus.CREATED);

	}

	@RequestMapping(path = "/{date}", method = RequestMethod.GET)
	public ResponseEntity<?> getMeasurementFromDay(@PathVariable String date) {


		if (date.length() > 10) {
			LocalDateTime localDate = LocalDateTime.parse(date);

			MeasurementDTO dto = measurementService.getMeasurementByDatAndTime(localDate);

			if (dto != null) {
				return new ResponseEntity<>(dto, HttpStatus.OK);
			}

		} else if (date.length() <= 10) {
			LocalDate localDate = LocalDate.parse(date);

			List<MeasurementDTO> dto = measurementService.getMeasurementsFromSingleDay(localDate);

			if (!dto.isEmpty()) {
				return new ResponseEntity<>(dto, HttpStatus.OK);
			}

		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@RequestMapping(path = "/{date}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateMeasurement(@RequestBody @Valid MeasurementDTO measurmentDTO,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date)
			throws URISyntaxException {

		HttpHeaders responseHeaders = new HttpHeaders();

		try {
			MeasurementDTO updatedRecord = measurementService.updateMeasurment(measurmentDTO, date);

			if (updatedRecord != null) {
				return new ResponseEntity<>("", responseHeaders, HttpStatus.NO_CONTENT);
			}

		} catch (TimeStampsDontMatchException e) {
			return new ResponseEntity<>("", responseHeaders, HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>("", responseHeaders, HttpStatus.NOT_FOUND);

	}

	@RequestMapping(path = "/{date}", method = RequestMethod.PATCH)
	public ResponseEntity<?> updateMeasurementValues(@RequestBody @Valid MeasurementDTO measurmentDTO,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date)
			throws URISyntaxException {

		HttpHeaders responseHeaders = new HttpHeaders();

		try {
			MeasurementDTO updatedRecord = measurementService.updateMeasurment(measurmentDTO, date);

			if (updatedRecord != null) {
				return new ResponseEntity<>("", responseHeaders, HttpStatus.NO_CONTENT);
			}

		} catch (TimeStampsDontMatchException e) {
			return new ResponseEntity<>("", responseHeaders, HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>("", responseHeaders, HttpStatus.NOT_FOUND);

	}

	@RequestMapping(path = "/{date}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteMeasurementValues(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date)
			throws URISyntaxException {

		HttpHeaders responseHeaders = new HttpHeaders();
		
		MeasurementDTO dto = measurementService.getMeasurementByDatAndTime(date);
		
		if (dto != null) {
			measurementService.deleteMeasurement(date);
			return new ResponseEntity<>("", responseHeaders, HttpStatus.NO_CONTENT);
		}

		

		return new ResponseEntity<>("", responseHeaders, HttpStatus.NOT_FOUND);

	}

}
