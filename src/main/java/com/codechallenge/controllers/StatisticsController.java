package com.codechallenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codechallenge.dto.StatisticsDTO;
import com.codechallenge.service.StatisticsService;

@RestController
@RequestMapping("/stats")
public class StatisticsController {

	private StatisticsService statisticsService;

	@Autowired
	public StatisticsController(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;

	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<StatisticsDTO> addMeasurement(StatisticsDTO statisticsDTO) {

		List<StatisticsDTO> results = statisticsService.findStats(statisticsDTO);

		return results;

	}

}
