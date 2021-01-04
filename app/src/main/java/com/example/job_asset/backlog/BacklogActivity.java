package com.example.job_asset.backlog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job_asset.R;
import com.example.job_asset.common.JobPeriods;
import com.example.job_asset.job_creator.BusinessSingleJob;
import com.example.job_asset.job_creator.CreationActivityOptionals;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import static com.example.job_asset.common.Constants.REQUEST_CREATION_STAGE_TWO;

public class BacklogActivity extends AppCompatActivity {
	private static final String TAG = "backlog activity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_backlog);
		Log.i(TAG, "Activity started");
		
		initialize();
		
		Log.v(TAG, "Activity initialized.");
	}
	
	private void initialize() {
	
	}
	
}
