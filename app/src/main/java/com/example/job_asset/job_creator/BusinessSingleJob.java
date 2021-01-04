package com.example.job_asset.job_creator;

import androidx.annotation.NonNull;

import com.example.job_asset.common.SingleJob;

import java.io.Serializable;

public class BusinessSingleJob implements Serializable {
	public SingleJob jobCreating;
	private SingleJob jobMemento;
	
	BusinessSingleJob() {
		jobCreating = new SingleJob(1, "NOT_SPECIFIED", "");
		jobMemento = jobCreating;
	}
	
	void SaveState() {
		jobMemento = jobCreating;
	}
	
	void ResetState() {
		jobCreating = jobMemento;
	}
	
	boolean PrimaryDataExists() {
		return jobCreating.id >= 0;
	}
	boolean NeedleDataExists() {
		return PrimaryDataExists() &&
				jobCreating.creationDate != null;
	}
	boolean SaveJob() {
		return jobCreating.id >= 0;
	}
	@NonNull
	@Override
	public String toString() {
		return String.format("Creating: %s.", jobCreating.toString());
	}
}
