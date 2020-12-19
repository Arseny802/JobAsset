package com.example.job_asset.common;

public enum JobPriorities {
	UNKNOWN,
	LOW,
	MEDIUM,
	HIGH,
	URGENT;
	
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
}
