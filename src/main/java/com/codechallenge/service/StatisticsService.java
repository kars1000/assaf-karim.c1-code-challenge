package com.codechallenge.service;

import java.util.List;

import com.codechallenge.dto.StatisticsDTO;

public interface StatisticsService {
	
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	public List<StatisticsDTO> findStats(StatisticsDTO dto);
	
	
	
	

}
