package com.example.job_asset.common;

import androidx.annotation.NonNull;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.time.ZoneId;

public enum JobPeriods {
	MORNING,
	DAY,
	EVENING,
	NIGHT;
	
	@NonNull
	public static JobPeriods[] getArray() {
		return new JobPeriods[]{MORNING, DAY, EVENING, NIGHT};
	}
	@NonNull
	public static String[] getStringArray() {
		JobPeriods[] array = getArray();
		String[] result = new String[array.length];
		for (int index = 0; index < array.length; ++index) {
			result[index] = array[index].toString();
		}
		return result;
	}
	
	public static JobPeriods GetPeriodByTime(LocalTime time) {
		if (time.isBefore(Constants.MORNING_START)) {
			return NIGHT;
		}
		if (time.isBefore(Constants.MORNING_END)) {
			return MORNING;
		}
		if (time.isBefore(Constants.DAY_END)) {
			return DAY;
		}
		if (time.isBefore(Constants.EVENING_END)) {
			return EVENING;
		}
		return NIGHT;
	}
	
	public JobPeriods increment() {
		switch (this) {
			case MORNING:
				return DAY;
			case DAY:
				return EVENING;
			case EVENING:
			case NIGHT:
				return MORNING;
			default:
				return NIGHT;
		}
	}
	
	public JobPeriods decrement() {
		switch (this) {
			case NIGHT:
			case MORNING:
				return EVENING;
			case EVENING:
				return DAY;
			case DAY:
			default:
				return MORNING;
		}
	}
	
	@NonNull
	@Override
	public String toString() {
		switch (this) {
			case NIGHT:
				return "night";
			case MORNING:
				return "morning";
			case EVENING:
				return "evening";
			case DAY:
				return "day";
			default:
				return "unknown";
		}
	}
	@NonNull
	public static JobPeriods fromString(String period) {
		switch (period.toLowerCase()) {
			case "morning":
				return MORNING;
			case "day":
				return DAY;
			case "evening":
				return EVENING;
			case "night":
			default:
				return NIGHT;
		}
	}
	
}
