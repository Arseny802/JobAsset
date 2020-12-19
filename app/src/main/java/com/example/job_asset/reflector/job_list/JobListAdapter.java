package com.example.job_asset.reflector.job_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.job_asset.R;
import com.example.job_asset.common.SingleJob;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JobListAdapter extends RecyclerView.Adapter<SingleJobHolder> {
	
	private List<SingleJob> tweetList = new ArrayList<>();
	
	@Override
	public SingleJobHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.example_item, parent, false);
		return new SingleJobHolder(view);
	}
	
	@Override
	public void onBindViewHolder(SingleJobHolder holder, int position) {
		holder.bind(tweetList.get(position));
	}
	
	@Override
	public int getItemCount() {
		return tweetList.size();
	}
	
	public void setItems(Collection<SingleJob> tweets) {
		tweetList.addAll(tweets);
		notifyDataSetChanged();
	}
	
	public void clearItems() {
		tweetList.clear();
		notifyDataSetChanged();
	}
	
}
