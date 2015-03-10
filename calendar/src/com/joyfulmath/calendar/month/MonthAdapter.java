package com.joyfulmath.calendar.month;

import java.util.Calendar;
import java.util.List;

import com.joyfulmath.calendar.CalendarControl;
import com.joyfulmath.calendar.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MonthAdapter extends ArrayAdapter<String> {

	private static final String TAG = "calendar.MonthAdapter";
	private List<String> mString = null;
	private LayoutInflater mInflater = null;
	private MonthItemView mItemVeiw = null;
	public MonthAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		mString = objects;
		mInflater = LayoutInflater.from(context);
		CalendarControl.getinstance().initMonth();
		Log.i(TAG, "MonthAdapter");
	}
	
	public MonthAdapter(Context context,int resource)
	{
		super(context, resource);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Log.i(TAG, "getCount:"+MonthItemView.MAX_LINES_OF_MONTH);
		return MonthItemView.MAX_LINES_OF_MONTH;
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		Log.i(TAG, "getItem");
		return mString.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		Log.i(TAG, "getItemId");
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i(TAG, "[getView]:position:"+position);
		if(convertView==null)
		{
			convertView = mInflater.inflate(R.layout.month_list_item, null);
		}
		
		if(convertView instanceof MonthItemView)
		{
			MonthItemView itemview = (MonthItemView)convertView;
			itemview.setLines(position);
			itemview.postInvalidate();
		}
		

		Log.i(TAG, "[getView] string:"+mString.get(position));
		return convertView;
	}

	
	
	
}
