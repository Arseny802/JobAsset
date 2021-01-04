package com.example.job_asset.common;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Locale;

public class SingleJob implements Serializable {
	
	public int id;
	public String title;
	public String description;
	public JobPeriods targetPeriod;
	public JobPriorities priority;
	
	public LocalDateTime creationDate;
	
	public SingleJob(int id, String title, String description) {
		if (id <= 0) {
			throw new IllegalArgumentException("Job id must be greater than zero.");
		}
		if (title.isEmpty()) {
			throw new IllegalArgumentException("Job must have a title.");
		}
		creationDate = LocalDateTime.now(ZoneOffset.UTC);
		this.id = id;
		this.title = title;
		this.description = description;
		priority = JobPriorities.MEDIUM;
	}
	
	@NonNull
	@Override
	public String toString() {
		return String.format(Locale.getDefault(),
				"Job %s (id: %d), target period %s, priority %s, date creation %s.",
				title,
				id,
				targetPeriod.toString(),
				priority.toString(),
				creationDate.toString());
	}
}
