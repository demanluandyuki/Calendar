package com.joyfulmath.calendar.month;

import java.util.Calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MonthItemView extends View {

	public static final int MAX_LINES_OF_MONTH = 6;
	
	private static final String TAG = "calenar.MonthItemView";
	
	private int mLine  =-1;
	private int year = -1;
	private int month = -1;
	private int day = -1;
	private int dayofweek = -1;
	private int hour = -1;
	private int minute = -1;
	public MonthItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initDays();
	}

	public MonthItemView(Context context) {
		super(context);
		initDays();
	}
	
	public void setLines(int position)
	{
		mLine = position;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		
		//
		
		canvas.restore();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
		
	void initDays()
	{
		Calendar now = Calendar.getInstance();
		year = now.get(Calendar.YEAR);
		month = now.get(Calendar.MONTH);
		day = now.get(Calendar.DAY_OF_MONTH);
		dayofweek = now.get(Calendar.DAY_OF_WEEK);
		hour = now.get(Calendar.HOUR);
		minute = now.get(Calendar.MINUTE);
		Log.i(TAG, "time:"+now.getTime());
		Log.i(TAG, "dayofweek:"+dayofweek);
	}
	
}
