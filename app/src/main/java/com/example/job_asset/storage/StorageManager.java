package com.example.job_asset.storage;

import com.example.job_asset.common.SingleJob;

import java.util.Arrays;
import java.util.Collection;

public class StorageManager {
	
	public Collection<SingleJob> getMorningJobs() {
		return Arrays.asList(
				new SingleJob(1, "Morning task 1", "description 1"),
				new SingleJob(2, "Morning task 2", "description 2")
		);
	}
	
	public Collection<SingleJob> getDailyJobs() {
		return Arrays.asList(
				new SingleJob(3, "Daily job 1", "Very big description")
		);
	}
	
	public Collection<SingleJob> getEveningJobs() {
		return Arrays.asList(
				new SingleJob(11, "Evening task 1", "description 1"),
				new SingleJob(12, "Evening task 2", "description 2"),
				new SingleJob(13, "Evening task 3", "description 3"),
				new SingleJob(14, "Evening task 4", "description 4"),
				new SingleJob(15, "Evening task 5", "description 5"),
				new SingleJob(16, "Evening task 6", "description 6"),
				new SingleJob(17, "Evening task 7", "description 7")
		);
	}
}
