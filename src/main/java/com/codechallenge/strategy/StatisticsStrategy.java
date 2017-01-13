package com.codechallenge.strategy;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.codechallenge.dto.MeasurementDTO;
import com.codechallenge.dto.StatisticsDTO;

public abstract class StatisticsStrategy {

	public Double getValue(MeasurementDTO dto, String metric) {

		try {

			Object o = BeanUtils.getProperty(dto, metric);

			if (o != null) {
				return Double.parseDouble(BeanUtils.getProperty(dto, metric));
			}

			return null;

		} catch (Exception e) {

			throw new RuntimeException("Unable to find value...please check the property name and value");
		}

	}
	

	/**
	 * 
	 * @param measurments
	 * @param metric
	 * @param result
	 * @return
	 */
	public abstract StatisticsDTO calculate(List<MeasurementDTO> measurments, String metric);

}
