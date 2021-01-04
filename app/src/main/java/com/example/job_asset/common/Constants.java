package com.example.job_asset.common;

import java.time.Duration;
import java.time.LocalTime;

public class Constants {
	public static final int REQUEST_CREATION_STAGE_ONE = 11;
	public static final int REQUEST_CREATION_STAGE_TWO = 12;
	public static final int REQUEST_SETTING_ACTIVITY = 21;
	public static final int REQUEST_FEEDBACK_ACTIVITY = 31;
	public static final String REQUEST_RESULT_JOB_CREATION = "creating_job";
	
	public static final String SETTING_KEEP_ACTIVE = "keep.active";
	public static final String SETTING_KEEP_ARCHIVE = "keep.archive";
	
	public static final String DATABASE_NAME = "job_asset.db";
	
	public static final LocalTime MORNING_START = LocalTime.of(5, 0);
	public static final LocalTime MORNING_END = LocalTime.of(12, 0);
	public static final LocalTime DAY_START = MORNING_END;
	public static final LocalTime DAY_END = LocalTime.of(17, 0);
	public static final LocalTime EVENING_START = DAY_END;
	public static final LocalTime EVENING_END = LocalTime.of(23, 0);
	public static final LocalTime NIGHT_START = EVENING_END;
	public static final LocalTime NIGHT_END = MORNING_START;
	
	public static final JobPriorities DEFAULT_CREATION_PRIORITY = JobPriorities.MEDIUM;
	public static final LocalTime DefaultCreationMorningTime = LocalTime.of(9, 0);
	public static final LocalTime DefaultCreationDayTime = LocalTime.of(14, 0);
	public static final LocalTime DefaultCreationEveningTime = LocalTime.of(20, 0);
	
	public static final Duration KEEP_ACTIVE = Duration.ofDays(21);
	public static final Duration KEEP_ARCHIVE = Duration.ofDays(365);
}
