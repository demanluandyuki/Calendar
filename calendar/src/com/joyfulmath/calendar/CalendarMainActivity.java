package com.joyfulmath.calendar;


import com.joyfulmath.calendar.month.MonthFragment;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.WindowManager;

public class CalendarMainActivity extends Activity {

	private static final String TAG = "calendar.CalendarMainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "[onCreate]");
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.all_in_one);
		setFragment();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, "[onStart]");

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG, "[onStop]");
	}

	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "[onDestroy]");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calendar_main, menu);
		return true;
	}

	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Log.i(TAG, "[onBackPressed]");
	}

	public void setFragment() {
		Log.i(TAG, "[setFragment]");
		FragmentManager fm = this.getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
//		ft.replace(R.id.main_frame_id, new MonthFragment());
//		ft.addToBackStack("main_frame_id");
		ft.add(R.id.main_frame_id,new MonthFragment(),"main_frame_id");
		ft.commit();
		Log.i(TAG, "[setFragment] leave");
	}

}
