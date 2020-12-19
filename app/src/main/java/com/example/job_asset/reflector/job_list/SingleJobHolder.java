package com.example.job_asset.reflector.job_list;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.job_asset.R;
import com.example.job_asset.common.SingleJob;

public class SingleJobHolder extends RecyclerView.ViewHolder {
	private final TextView jobTitleTextView;
	private final TextView jobDescriptionTextView;
	
	public SingleJobHolder(View itemView) {
		super(itemView);
		
		jobTitleTextView = itemView.findViewById(R.id.textView);
		jobDescriptionTextView = itemView.findViewById(R.id.textView2);
	}
	
	public void bind(SingleJob data) {
		jobTitleTextView.setText(data.title);
		jobDescriptionTextView.setText(data.description);
	}
}
