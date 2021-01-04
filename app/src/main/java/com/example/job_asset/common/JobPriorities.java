package com.example.job_asset.common;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum JobPriorities {
	UNKNOWN,
	LOW,
	MEDIUM,
	HIGH,
	URGENT;
	
	@NonNull
	public static JobPriorities[] getArray() {
		return new JobPriorities[]{LOW, MEDIUM, HIGH, URGENT};
	}
	@NonNull
	public static String[] getStringArray() {
		JobPriorities[] array = getArray();
		String[] result = new String[array.length];
		for (int index = 0; index < array.length; ++index) {
			result[index] = array[index].toString();
		}
		return result;
	}
	public JobPriorities increment(JobPriorities current) {
		switch (current) {
			case URGENT:
			case HIGH:
				return URGENT;
			case MEDIUM:
				return HIGH;
			case LOW:
				return MEDIUM;
			case UNKNOWN:
			default:
				return UNKNOWN;
		}
	}
	
	public JobPriorities decrement(JobPriorities current) {
		switch (current) {
			case URGENT:
				return HIGH;
			case HIGH:
				return MEDIUM;
			case MEDIUM:
			case LOW:
				return LOW;
			case UNKNOWN:
			default:
				return UNKNOWN;
		}
	}
	
	public int toInteger(JobPriorities current) {
		for (Map.Entry<Integer, JobPriorities> item : PRIORITY_CODES.entrySet()) {
			if (item.getValue() == current) {
				return item.getKey();
			}
		}
		return 0;
	}
	
	public JobPriorities fromInteger(int current) {
		return PRIORITY_CODES.containsKey(current) ?
			PRIORITY_CODES.get(current) : PRIORITY_CODES.get(0);
	}
	
	private static final Map<Integer, JobPriorities> PRIORITY_CODES;
	static {
		Map<Integer, JobPriorities> aMap = new HashMap<>();
		aMap.put(0, JobPriorities.UNKNOWN);
		aMap.put(1, JobPriorities.LOW);
		aMap.put(2, JobPriorities.MEDIUM);
		aMap.put(3, JobPriorities.HIGH);
		aMap.put(4, JobPriorities.URGENT);
		PRIORITY_CODES = Collections.unmodifiableMap(aMap);
	}
	
	@NonNull
	@Override
	public String toString() {
		switch (this) {
			case LOW:
				return "low";
			case MEDIUM:
				return "medium";
			case HIGH:
				return "high";
			case URGENT:
				return "urgent";
			case UNKNOWN:
			default:
				return "unknown";
		}
	}
	@NonNull
	public static JobPriorities fromString(String period) {
		switch (period.toLowerCase()) {
			case "low":
				return LOW;
			case "medium":
				return MEDIUM;
			case "high":
				return HIGH;
			case "urgent":
				return URGENT;
			case "unknown":
			default:
				return UNKNOWN;
		}
	}
}
