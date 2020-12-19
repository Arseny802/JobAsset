package com.example.job_asset.common;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class SingleJob {
	
	public int id;
	public String title;
	public String description;
	JobPriorities priority;
	
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
}
