package com.example.job_asset.reflector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.job_asset.R;
import com.example.job_asset.common.SingleJob;
import com.example.job_asset.reflector.job_list.JobList;
import com.example.job_asset.reflector.job_list.JobListAdapter;
import com.example.job_asset.storage.StorageManager;

import java.util.Collection;

import static java.lang.String.format;

public class ReflectorActivity extends AppCompatActivity {
	
	private static final String TAG = "Main app activity";
	
	private static final StorageManager storageManager = new StorageManager();
	private static final JobList jobs_morning = new JobList();
	private static final JobList jobs_day = new JobList();
	private static final JobList jobs_evening = new JobList();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "Started application.");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reflector);
		Log.i(TAG, "Initialized application.");
		
		initializeList(jobs_morning, R.id.MorningJobs, storageManager.getMorningJobs());
		initializeList(jobs_day, R.id.DailyJobs, storageManager.getDailyJobs());
		initializeList(jobs_evening, R.id.EveningJobs, storageManager.getEveningJobs());
		
		Log.i(TAG, "Running application.");
	}
	
	private void initializeList(JobList list, int id, Collection<SingleJob> existingItems) {
		list.recyclerView = (RecyclerView) findViewById(id);
		list.recyclerView.setLayoutManager(new LinearLayoutManager(this));
		list.exampleListAdapter = new JobListAdapter();
		list.recyclerView.setAdapter(list.exampleListAdapter);
		
		if (existingItems.isEmpty()) {
			Log.w(TAG, format("Got %d items.", existingItems.size()));
		} else {
			Log.i(TAG, format("Got %d items.", existingItems.size()));
			list.exampleListAdapter.setItems(existingItems);
		}
	}
}