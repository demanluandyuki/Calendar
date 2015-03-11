package com.joyfulmath.calendar.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Tool {
	
	public static int mScreenWidth = -1;
	public static int mScreenHeight = -1;
	public static int CALENDAR_CELL_WIDTH = 100;
	public static final int CALENDAR_CELL_HEIGHT = 200;
	public static final int CALENDAR_CELL_DIFF = 2;
	public static final int WEEK_DAY_LENGTH = 7;
	private Context mContext = null;
	public Tool(Context context)
	{
		mContext = context;
	}
	
	public void initParams() {
		WindowManager wm = (WindowManager) mContext.getSystemService(
				Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;
		mScreenHeight = dm.heightPixels;
		CALENDAR_CELL_WIDTH = (mScreenWidth - (WEEK_DAY_LENGTH-1) * CALENDAR_CELL_DIFF)
				/ WEEK_DAY_LENGTH;

	}
}
