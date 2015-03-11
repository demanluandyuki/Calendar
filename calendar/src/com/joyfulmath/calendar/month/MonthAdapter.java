package com.joyfulmath.calendar.month;

import java.util.Calendar;
import java.util.List;

import com.joyfulmath.calendar.CalendarControl;
import com.joyfulmath.calendar.R;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MonthAdapter extends ArrayAdapter<String> {

	private static final String TAG = "calendar.MonthAdapter";
	private List<String> mString = null;
	private LayoutInflater mInflater = null;
	private MonthItemView mItemVeiw = null;
//	private int mScreenWidth = -1;
//	private int mScreenHeight = -1;
	public MonthAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		mString = objects;
		mInflater = LayoutInflater.from(context);
		CalendarControl.getinstance().initMonth();
		initParams();
		Log.i(TAG, "MonthAdapter");
	}
	
	public MonthAdapter(Context context,int resource)
	{
		super(context, resource);
		mInflater = LayoutInflater.from(context);
		CalendarControl.getinstance().initMonth();
		initParams();
	}
	
	
	public void initParams() {
		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		CalendarControl.mScreenWidth = dm.widthPixels;
		CalendarControl.mScreenHeight = dm.heightPixels;
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
