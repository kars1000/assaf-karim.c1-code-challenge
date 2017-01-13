package com.codechallenge.strategy;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.codechallenge.dto.MeasurementDTO;
import com.codechallenge.dto.StatisticsDTO;

@Service("min")
public class MinStartegy extends StatisticsStrategy {

	@Override
	public StatisticsDTO calculate(List<MeasurementDTO> measurments, String metric) {

		if (measurments.stream().filter(e -> getValue(e, metric) != null).count() > 0) {
			double min = measurments.stream().filter(e -> getValue(e, metric) != null).mapToDouble(e -> getValue(e, metric)).min().getAsDouble();
			
			StatisticsDTO result = new StatisticsDTO();

			result.setMin(new DecimalFormat("#.##").format(min));

			return result;
		}

		return null;

	}

}
