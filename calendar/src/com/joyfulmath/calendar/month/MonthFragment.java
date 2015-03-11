package com.joyfulmath.calendar.month;

import java.util.ArrayList;
import java.util.List;

import com.joyfulmath.calendar.R;
import com.joyfulmath.calendar.util.Tool;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MonthFragment extends Fragment {

	private static final String TAG = "calendar.MonthFragment";
	private MonthListView mList = null;
	private MonthAdapter mAdapter = null;
	private List<String> mStrings = new ArrayList<String>();
	private Context mContext = null;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
		initStrings();
		Tool mTools = new Tool(mContext);
		mTools.initParams();
		Log.i(TAG, "[onAttach] leave");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "[onCreate] leave");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "[onCreateView]");
		View v;
		v = inflater.inflate(R.layout.month_by_week, container, false);
		mList = (MonthListView) v.findViewById(R.id.month_list);
		View headview = inflater
				.inflate(R.layout.month_list_head, mList, false);
		mList.addHeaderView(headview);
		mAdapter = new MonthAdapter(mContext, R.layout.month_list_item,
				mStrings);
		mList.setAdapter(mAdapter);
		Log.i(TAG, "[onCreateView] leave");
		
		return v;
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

	public void initStrings() {
		mStrings.clear();
		mStrings.add("1");
		mStrings.add("2");
		mStrings.add("3");
		mStrings.add("4");
		mStrings.add("5");
		mStrings.add("6");
	}

}
