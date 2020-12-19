package com.example.job_asset.common;

import java.time.LocalTime;

public class Constants {
	public static final String vdsfv = "";
	
	public static final LocalTime MorningStart = LocalTime.of(5, 0);
	public static final LocalTime MorningEnd = LocalTime.of(12, 0);
	public static final LocalTime DayStart = MorningEnd;
	public static final LocalTime DayEnd = LocalTime.of(17, 0);
	public static final LocalTime EveningStart = DayEnd;
	public static final LocalTime EveningEnd = LocalTime.of(23, 0);
	public static final LocalTime NightStart = EveningEnd;
	public static final LocalTime NightEnd = MorningStart;
	
	public static final LocalTime DefaultCreationMorningTime = LocalTime.of(9, 0);
	public static final LocalTime DefaultCreationDayTime = LocalTime.of(14, 0);
	public static final LocalTime DefaultCreationEveningTime = LocalTime.of(20, 0);
}
