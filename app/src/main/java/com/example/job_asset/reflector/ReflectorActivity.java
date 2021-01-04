package com.example.job_asset.reflector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.job_asset.R;
import com.example.job_asset.common.SingleJob;
import com.example.job_asset.job_creator.CreationActivity;
import com.example.job_asset.reflector.job_list.JobList;
import com.example.job_asset.reflector.job_list.JobListAdapter;
import com.example.job_asset.storage.StorageManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collection;

import static com.example.job_asset.common.Constants.REQUEST_CREATION_STAGE_ONE;
import static com.example.job_asset.common.Constants.REQUEST_CREATION_STAGE_TWO;
import static com.example.job_asset.common.Constants.REQUEST_FEEDBACK_ACTIVITY;
import static com.example.job_asset.common.Constants.REQUEST_SETTING_ACTIVITY;
import static java.lang.String.format;

public class ReflectorActivity extends AppCompatActivity {
	
	private static final String TAG = "Main app activity";
	
	private static final StorageManager storageManager = new StorageManager();
	private static final JobList jobs_morning = new JobList();
	private static final JobList jobs_day = new JobList();
	private static final JobList jobs_evening = new JobList();
	private static FloatingActionButton actionButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reflector);
		Log.i(TAG, "Initialized application.");
		
		initializeList(jobs_morning, R.id.MorningJobs, storageManager.getMorningJobs());
		initializeList(jobs_day, R.id.DailyJobs, storageManager.getDailyJobs());
		initializeList(jobs_evening, R.id.EveningJobs, storageManager.getEveningJobs());
		initializeAdditionButton();
		
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
	
	private void initializeAdditionButton() {
		actionButton = (FloatingActionButton) findViewById(R.id.ReflectorActivity_addJob);
		
		Intent intent = new Intent(this, CreationActivity.class);
		actionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
				
				
				//Intent intent = new Intent(this, CreationActivity.class);
				//intent.putExtra(EXTRA_MESSAGE, message);
				startActivityForResult(intent, REQUEST_CREATION_STAGE_ONE);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (requestCode) {
			case REQUEST_CREATION_STAGE_ONE: {
				if (data != null && resultCode == RESULT_OK) {
					if (data.getBooleanExtra("saved", false)) {
						Snackbar.make(findViewById(R.id.ReflectorActivity),
								"Job successfully created", Snackbar.LENGTH_LONG)
								.setAction("Action", null).show();
						return;
					}
				} else if (resultCode == RESULT_CANCELED) {
					Snackbar.make(findViewById(R.id.ReflectorActivity),
							"Job creation was cancelled", Snackbar.LENGTH_LONG)
							.setAction("Action", null).show();
					return;
				}
				
				Snackbar.make(findViewById(R.id.ReflectorActivity),
						"Job was not created", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
				return;
			}
			case REQUEST_CREATION_STAGE_TWO:
				Log.e(TAG, "Got result from foreign activity!");
				return;
			case REQUEST_SETTING_ACTIVITY:
			case REQUEST_FEEDBACK_ACTIVITY:
			default:
				Log.e(TAG, "Got result from unimplemented activity!");
		}
	}
}