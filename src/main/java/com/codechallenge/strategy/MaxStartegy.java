package com.codechallenge.strategy;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.codechallenge.dto.MeasurementDTO;
import com.codechallenge.dto.StatisticsDTO;

@Service("max")
public class MaxStartegy extends StatisticsStrategy {

	@Override
	public StatisticsDTO calculate(List<MeasurementDTO> measurments, String metric) {
		
		

		if (measurments.stream().filter(e -> getValue(e, metric) != null).count() > 0) {
			double max = measurments.stream().filter(e -> getValue(e, metric) != null)
					.mapToDouble(e -> getValue(e, metric)).max().getAsDouble();
			
			StatisticsDTO result = new StatisticsDTO();

			result.setMax(new DecimalFormat("#.##").format(max));

			return result;
		}

		return null;
	}

}
