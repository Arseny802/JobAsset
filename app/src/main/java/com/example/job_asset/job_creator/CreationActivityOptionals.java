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

import static com.example.job_asset.common.Constants.REQUEST_CREATION_STAGE_TWO;
import static com.example.job_asset.common.Constants.REQUEST_RESULT_JOB_CREATION;

public class CreationActivityOptionals extends AppCompatActivity {
	private static final String TAG = "creation activity stage two";
	BusinessSingleJob businessSingleJob;
	private Spinner prioritySpinner;
	private Spinner periodTargetSpinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creation_2);
		Log.i(TAG, "Activity started");
		
		businessSingleJob = (BusinessSingleJob)getIntent().
				getSerializableExtra(REQUEST_RESULT_JOB_CREATION);
		
		Snackbar.make(findViewById(R.id.CreationActivityTwo),
				businessSingleJob.toString(), Snackbar.LENGTH_LONG)
				.setAction("Action", null).show();
		
		
		initializeSelectableDate();
		initializeEditionDescription();
		initializeReturnButton();
		initializeForwardButton();
		
		Log.v(TAG, "Activity initialized.");
	}
	
	private void initializeSelectableDate() {
	}
	
	private void initializeEditionDescription() {
	}
	
	private void initializeReturnButton() {
		findViewById(R.id.CreationActivityTwo_cancelButton).setOnClickListener(
				view -> {
					setResult(RESULT_CANCELED, null);
					finish();
				});
	}
	
	private void initializeForwardButton() {
		findViewById(R.id.CreationActivityTwo_continueButton).setOnClickListener(view -> {
			
			//businessSingleJob.jobCreating.priority = JobPriorities.fromString(
			//		prioritySpinner.getSelectedItem().toString());
			
			if (!businessSingleJob.NeedleDataExists()) {
				Snackbar.make(view, "Not all data exists", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
				
				return;
			}
			
			Intent returnData = new Intent();
			returnData.putExtra(REQUEST_RESULT_JOB_CREATION, businessSingleJob);
			setResult(RESULT_OK, returnData);
			finish();
		});
	}
}
