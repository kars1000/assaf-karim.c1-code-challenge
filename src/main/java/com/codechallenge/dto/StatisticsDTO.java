package com.codechallenge.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class StatisticsDTO {

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fromDateTime;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime toDateTime;

	private List<String> stat;

	private List<String> metric;

	private String max;

	private String min;

	private String average;

	private String val;

	@JsonProperty("metric")
	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public LocalDateTime getFromDateTime() {
		return fromDateTime;
	}

	public void setFromDateTime(LocalDateTime fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	public LocalDateTime getToDateTime() {
		return toDateTime;
	}

	public void setToDateTime(LocalDateTime toDateTime) {
		this.toDateTime = toDateTime;
	}

	public List<String> getStat() {
		return stat;
	}

	public void setStat(List<String> stat) {
		this.stat = stat;
	}

	public List<String> getMetric() {
		return metric;
	}

	public void setMetric(List<String> metric) {
		this.metric = metric;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getAverage() {
		return average;
	}

	public void setAverage(String average) {
		this.average = average;
	}

	@Override
	public String toString() {
		return "StatisticsDTO [max=" + max + ", min=" + min + ", average=" + average + ", metric=" + metric + "]";
	}

}
