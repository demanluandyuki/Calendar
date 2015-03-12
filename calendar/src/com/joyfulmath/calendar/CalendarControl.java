package com.joyfulmath.calendar;

import java.util.Calendar;

import com.joyfulmath.calendar.month.MonthItem;
import com.joyfulmath.calendar.util.Tool;

import android.util.Log;

public class CalendarControl {

	public static final int MOVE_LAST_MONTH = 1;
	public static final int MOVE_NEXT_MONTH = 2;
	
	private static final String TAG = "calendar.CalendarControl";
	private static CalendarControl mInstance = null;
	private int monthDaySize = -1;
	private int firstDayindex = -1;
	private int lastMonthDaySize = -1;
	private int mEnableLines = -1;
	private int nextLines = -1;
	private int currentDay = -1;
	private Calendar mShowMonth = null;
	public static CalendarControl getinstance() {
		if (mInstance == null) {
			mInstance = new CalendarControl();
		}
		return mInstance;
	}

	private CalendarControl() {
		
	}
		
	public int setMonth(Calendar showMonth)
	{
		Calendar cal = showMonth;
		
		firstDayindex = cal.get(Calendar.DAY_OF_WEEK);
		monthDaySize = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		Log.i(TAG, "[setMonth]monthDaySize:" + monthDaySize);

		cal.add(Calendar.DAY_OF_MONTH, -1);// last month
		lastMonthDaySize = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		mEnableLines = (firstDayindex + monthDaySize - 1) / Tool.WEEK_DAY_LENGTH;

		nextLines = (firstDayindex + monthDaySize - 1) % Tool.WEEK_DAY_LENGTH;
		if (nextLines > 0) {
			mEnableLines = mEnableLines + 1;
		}
		
		Log.i(TAG, "[setMonth]firstDayindex:" + firstDayindex
				+ " mEnableLines:" + mEnableLines + " monthDaySize:"
				+ monthDaySize + " nextLines:" + nextLines);
		return firstDayindex;
	}
		
	public int initMonth(Calendar showMonth) {
		int firstday = -1;
		Calendar currentCal = Calendar.getInstance();
		currentDay = currentCal.get(Calendar.DAY_OF_MONTH);
		Log.i(TAG, "[initMonth]currentDay:" + currentDay);
		if(showMonth == null)
		{
			currentCal.set(Calendar.DAY_OF_MONTH, 1);
			mShowMonth = (Calendar) currentCal.clone();
			firstday = setMonth(currentCal);

		}
		else
		{
			showMonth.set(Calendar.DAY_OF_MONTH, 1);
			mShowMonth = (Calendar) showMonth.clone();
			firstday = setMonth(showMonth);

		}
		
		return firstday;
	}

	public void moveToLastMonth()
	{
		Log.i(TAG, "[moveToLastMonth]mShowMonth:"+mShowMonth.getTime());
		if(mShowMonth==null)
		{
			mShowMonth = Calendar.getInstance();
			mShowMonth.set(Calendar.DAY_OF_MONTH, 1);
		}

		//get last month
		mShowMonth.add(Calendar.MONTH, -1);
		Calendar cal = (Calendar) mShowMonth.clone();
		setMonth(cal);
	}
		
	public void moveToNextMonth()
	{
		Log.i(TAG, "[moveToNextMonth]mShowMonth:"+mShowMonth.getTime());
		if(mShowMonth==null)
		{
			mShowMonth = Calendar.getInstance();
			mShowMonth.set(Calendar.DAY_OF_MONTH, 1);
		}
		
		//get last month
		mShowMonth.add(Calendar.MONTH, 1);
		Calendar cal = (Calendar) mShowMonth.clone();
		setMonth(cal);
	}
	
	public String getShowMonthText()
	{
		if(mShowMonth == null)
		{
			Log.w(TAG, "mShowMonth is null");
			return null;
		}
		
		String text = null;
		
		text = String.format("%d/%d", mShowMonth.get(Calendar.YEAR),mShowMonth.get(Calendar.MONTH)+1);
		Log.i(TAG, "mShowMonth:"+mShowMonth.getTime());
		return text;
		
	}
	
	public MonthItem getMonthShowItem(int position, int row)// row form 1---7
	{
		Log.d(TAG, "[getMonthShowItem]position:" + position + " row:" + row);
		if (row < 1 || row > 7) {
			Log.w(TAG, "[getMonthShowItem]row out of rang");
			return null;
		}

		MonthItem item = new MonthItem();
		if (position == 0) {
			if (row < firstDayindex) {
				// last month
				item.index = row;
				item.mDay = String.valueOf(lastMonthDaySize + 1
						- (firstDayindex - row));
				item.mEnable = false;
			} else {
				// current month
				item.index = row;
				int day = row+1-firstDayindex;
				item.mDay = String.valueOf(day);
				item.mEnable = true;
				
				if(day == currentDay)
				{
					item.current = true;
				}
				
			}
		} else if (position < (mEnableLines - 1)) {
			// enable lines
			item.index = row;
			int day = row + position * Tool.WEEK_DAY_LENGTH+1-firstDayindex;
			item.mDay = String.valueOf(day);
			item.mEnable = true;
			if(day == currentDay)
			{
				item.current = true;
			}
		} else if (position == (mEnableLines - 1)) {
			if(nextLines == 0)
			{
				//current month and last month
				item.index = row;
				int day = row + position * Tool.WEEK_DAY_LENGTH+1-firstDayindex;
				item.mDay = String.valueOf(day);
				item.mEnable = true;
				if(day == currentDay)
				{
					item.current = true;
				}
			}
			else
			{
				if (row <= (nextLines)) {
					// current month
					item.index = row;
					int day = row + position * Tool.WEEK_DAY_LENGTH+1-firstDayindex;
					item.mDay = String.valueOf(day);
					item.mEnable = true;
					if(day == currentDay)
					{
						item.current = true;
					}
				} else {
					// next month
					int day = row + position * Tool.WEEK_DAY_LENGTH - firstDayindex
							- monthDaySize+1;
					item.index = row;
					item.mDay = String.valueOf(day);
					item.mEnable = false;
				}
			}
			
			
			
		} else if (position > (mEnableLines - 1)) {
			// next month
			int day = row + position * Tool.WEEK_DAY_LENGTH - firstDayindex
					- monthDaySize+1;
			item.index = row;
			item.mDay = String.valueOf(day);
			item.mEnable = false;
		}
		Log.d(TAG, "[getMonthShowItem]Levae item.mDay:" + item.mDay);
		return item;
	}
}
