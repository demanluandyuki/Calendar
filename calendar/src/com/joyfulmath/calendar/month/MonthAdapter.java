package com.joyfulmath.calendar.month;


import com.joyfulmath.calendar.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MonthAdapter extends ArrayAdapter<String> {

	private static final String TAG = "calendar.MonthAdapter";
	private LayoutInflater mInflater = null;

	public MonthAdapter(Context context, int resource) {
		super(context, resource);
		mInflater = LayoutInflater.from(context);
	}


	@Override
	public int getCount() {
		Log.i(TAG, "getCount:" + MonthItemView.MAX_LINES_OF_MONTH);
		return MonthItemView.MAX_LINES_OF_MONTH;
	}

	@Override
	public String getItem(int position) {
		Log.i(TAG, "getItem");
		return null;
	}

	@Override
	public long getItemId(int position) {
		Log.i(TAG, "getItemId");
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i(TAG, "[getView]:position:" + position);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.month_list_item, null);
		}

		if (convertView instanceof MonthItemView) {
			MonthItemView itemview = (MonthItemView) convertView;
			itemview.setLines(position);
			itemview.postInvalidate();
		}
		return convertView;
	}

}
