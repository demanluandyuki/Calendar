package com.joyfulmath.calendar.month;


import com.joyfulmath.calendar.CalendarControl;
import com.joyfulmath.calendar.R;
import com.joyfulmath.calendar.util.Tool;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MonthFragment extends Fragment {

	private static final String TAG = "calendar.MonthFragment";
	private MonthListView mList = null;
	private TextView title = null;
	private MonthAdapter mAdapter = null;
	private Context mContext = null;
	private View mMontWeekView = null;
	
	Handler mhanHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			Log.i(TAG, "[handleMessage]");
			switch(msg.what)
			{
			case CalendarControl.MOVE_LAST_MONTH:
				CalendarControl.getinstance().moveToLastMonth();
				break;
			case CalendarControl.MOVE_NEXT_MONTH:
				CalendarControl.getinstance().moveToNextMonth();
				break;
			}
			
			MonthFragment.this.updateView();

		}
		
	};
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
		Tool mTools = new Tool(mContext);
		mTools.initParams();
		Log.i(TAG, "[onAttach] leave");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CalendarControl.getinstance().initMonth(null);
		Log.i(TAG, "[onCreate] leave");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "[onCreateView]");
		mMontWeekView = inflater.inflate(R.layout.month_by_week, container, false);
		mList = (MonthListView) mMontWeekView.findViewById(R.id.month_list);
		View headview = inflater
				.inflate(R.layout.month_list_head, mList, false);
		mList.addHeaderView(headview);
		mList.setHandle(mhanHandler);
		mAdapter = new MonthAdapter(mContext, R.layout.month_list_item);
		mList.setAdapter(mAdapter);
		Log.i(TAG, "[onCreateView] leave");
		
		title = (TextView) mMontWeekView.findViewById(R.id.month_title);
		title.setText(CalendarControl.getinstance().getShowMonthText());
		return mMontWeekView;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "[onDestroy]");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.i(TAG, "[onDetach]");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(TAG, "[onPause]");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG, "[onResume]");
	}

	public void updateView()
	{
		mAdapter.notifyDataSetChanged();
		title.setText(CalendarControl.getinstance().getShowMonthText());
		title.invalidate();
	}
	
}
