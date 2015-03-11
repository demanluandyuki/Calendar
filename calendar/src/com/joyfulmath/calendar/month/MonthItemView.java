package com.joyfulmath.calendar.month;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.joyfulmath.calendar.CalendarControl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MonthItemView extends View {

	public static final int MAX_LINES_OF_MONTH = 6;
	
	private static final String TAG = "calendar.MonthItemView";
	
	private int mIndex  =-1;
	private int year = -1;
	private int month = -1;
	private int day = -1;
	private int dayofweek = -1;
	private int hour = -1;
	private int minute = -1;
	private Calendar nowCal = null;
	private CalendarControl mControl = null;
	private List<Integer> mString = new ArrayList<Integer>();
	
	private Paint mShowPaint = null;
	private Paint mHPaint = null;
	private Paint mRBPaint = null;
	private int mRectWidth = 100;
	public MonthItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setWillNotDraw(false);
		Log.i(TAG, "[MonthItemView] constructor");
		
		mControl = CalendarControl.getinstance();
		mString.clear();
		mShowPaint = new Paint();
		mShowPaint.setColor(Color.BLACK);
		
		mRBPaint = new Paint();
		mRBPaint.setColor(Color.BLUE);
		
		mHPaint = new Paint();
		mHPaint.setColor(Color.GRAY);
	}

	public MonthItemView(Context context) {
		super(context);

	}
	
	public void setLines(int position,int screenWidth,int screenHeight)
	{
		mIndex = position;
		Log.i(TAG, "[setLines]mLine:"+mIndex);
	}

	public void setLines(int position)
	{
		mIndex = position;
		Log.i(TAG, "[setLines]mLine:"+mIndex);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		Log.i(TAG, "[onDraw]");
		super.onDraw(canvas);
		canvas.save();
		for(int i=1;i<=CalendarControl.WEEK_DAY_LENGTH;i++)
		{
			MonthItem item = mControl.getMonthShowItem(mIndex, i);
			canvas.save();
			int left = (i-1)*100;
			int top = mIndex*100;
			int right = left+100;
			int bottom = top+100;		
			canvas.drawRect(left, top, right, bottom, mRBPaint);
			if(item.mEnable)
			{
				canvas.drawText(item.mDay, left+20, top+10, mShowPaint);
			}
			else
			{
				canvas.drawText(item.mDay, left+20, top+10, mHPaint);
			}
			canvas.restore();
		}
				
		canvas.restore();
		Log.i(TAG, "[onDraw] leave");
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		Log.i(TAG, String.format("[onMeasure]widthMeasureSpec:0X%x", widthMeasureSpec));
//		Log.i(TAG, "[onMeasure]heightMeasureSpec:"+heightMeasureSpec);
	}
		

	
}
