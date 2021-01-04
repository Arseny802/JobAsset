package com.example.job_asset.job_creator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job_asset.R;
import com.example.job_asset.common.JobPeriods;
import com.example.job_asset.common.JobPriorities;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalTime;
import java.time.ZoneId;

import static com.example.job_asset.common.Constants.DEFAULT_CREATION_PRIORITY;
import static com.example.job_asset.common.Constants.REQUEST_CREATION_STAGE_TWO;
import static com.example.job_asset.common.Constants.REQUEST_RESULT_JOB_CREATION;

public class CreationActivity extends AppCompatActivity {
	private static final String TAG = "creation activity";
	private BusinessSingleJob businessSingleJob;
	private Spinner prioritySpinner;
	private Spinner periodTargetSpinner;
	private static FloatingActionButton returnButton;
	private static FloatingActionButton forwardButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creation_1);
		Log.i(TAG, "Activity started");
		
		businessSingleJob = new BusinessSingleJob();
		
		initializeSelectablePeriod();
		initializeSelectablePriority();
		initializeReturnButton();
		initializeForwardButton();
		Log.v(TAG, "Activity initialized.");
	}
	
	private void initializeSelectablePeriod() {
		ArrayAdapter<String> adapter = new ArrayAdapter<>(
				this,
				android.R.layout.simple_spinner_item,
				JobPeriods.getStringArray()
		);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		final JobPeriods period = JobPeriods.GetPeriodByTime(
				LocalTime.now(ZoneId.systemDefault())).increment();
		final int defaultPosition = adapter.getPosition(period.toString());
		
		periodTargetSpinner = findViewById(R.id.CreationActivity_periodTarget);
		periodTargetSpinner.setAdapter(adapter);
		periodTargetSpinner.setSelection(defaultPosition);
	}
	
	private void initializeSelectablePriority() {
		ArrayAdapter<String> adapter = new ArrayAdapter<>(
				this,
				android.R.layout.simple_spinner_item,
				JobPriorities.getStringArray()
		);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		final int defaultPosition = adapter.getPosition(DEFAULT_CREATION_PRIORITY.toString());
		
		prioritySpinner = findViewById(R.id.CreationActivity_priority);
		prioritySpinner.setAdapter(adapter);
		prioritySpinner.setSelection(defaultPosition);
	}
	
	private void initializeReturnButton() {
		findViewById(R.id.CreationActivity_cancelButton).setOnClickListener(
				view -> {
					setResult(RESULT_CANCELED, null);
					finish();
				});
	}
	
	private void initializeForwardButton() {
		forwardButton = (FloatingActionButton) findViewById(R.id.CreationActivity_continueButton);
		
		Intent intentCreationActivityOptionals = new Intent(this, CreationActivityOptionals.class);
		forwardButton.setOnClickListener(view -> {
			
			businessSingleJob.jobCreating.priority = JobPriorities.fromString(
					prioritySpinner.getSelectedItem().toString());
			businessSingleJob.jobCreating.targetPeriod = JobPeriods.fromString(
					periodTargetSpinner.getSelectedItem().toString());
			
			if (!businessSingleJob.PrimaryDataExists()) {
				Snackbar.make(view, "Not all data exists", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
				
				return;
			}
			
			intentCreationActivityOptionals.putExtra(REQUEST_RESULT_JOB_CREATION, businessSingleJob);
			startActivityForResult(intentCreationActivityOptionals, REQUEST_CREATION_STAGE_TWO);
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == REQUEST_CREATION_STAGE_TWO) {
			Log.i(TAG, "Got result from stage two activity.");
			
			if (resultCode == RESULT_CANCELED) {
				Log.i(TAG, "Creation cancelled.");
				return;
			}
			
			if (data == null) {
				Log.w(TAG, "No data returned");
				return;
			}
			businessSingleJob = (BusinessSingleJob)data.getSerializableExtra(REQUEST_RESULT_JOB_CREATION);
			
			if (!businessSingleJob.NeedleDataExists()) {
				Log.e(TAG, "Result from stage two activity is not full!");
				forwardButton.callOnClick();
				return;
			}
			
			Intent returnData = new Intent();
			returnData.putExtra("formatted", true);
			returnData.putExtra("saved", businessSingleJob.SaveJob());
			setResult(RESULT_OK, returnData);
			finish();
		} else {
			Log.e(TAG, "Got result from unknown activity!");
		}
	}
}
