package com.joyfulmath.calendar;

import java.util.Calendar;
import java.util.Date;

import com.joyfulmath.calendar.month.MonthItem;
import com.joyfulmath.calendar.util.Tool;

import android.util.Log;

public class CalendarControl {

	private static final String TAG = "calendar.CalendarControl";
	private static CalendarControl mInstance = null;
	private int monthDaySize = -1;
	private int firstDayindex = -1;
	private int lastMonthDaySize = -1;
	private int mEnableLines = -1;
	private int nextLines = -1;
	private int currentDay = -1;
	public static CalendarControl getinstance() {
		if (mInstance == null) {
			mInstance = new CalendarControl();
		}
		return mInstance;
	}

	private CalendarControl() {

	}

	public int initMonth() {
		Calendar cal = Calendar.getInstance();
		currentDay = cal.get(Calendar.DAY_OF_MONTH);
		Log.i(TAG, "[getFirstDay]currentDay:" + currentDay);
		
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Log.i(TAG, "[getFirstDay]cal:" + cal.getTime());
		
		firstDayindex = cal.get(Calendar.DAY_OF_WEEK);
		monthDaySize = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		Log.i(TAG, "[getFirstDay]monthDaySize:" + monthDaySize);

		cal.add(Calendar.DAY_OF_MONTH, -1);// last month
		lastMonthDaySize = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		mEnableLines = (firstDayindex + monthDaySize - 1) / Tool.WEEK_DAY_LENGTH;

		nextLines = (firstDayindex + monthDaySize - 1) % Tool.WEEK_DAY_LENGTH;
		if (nextLines > 0) {
			mEnableLines = mEnableLines + 1;
		}
		Log.i(TAG, "[initMonth]firstDayindex:" + firstDayindex
				+ " mEnableLines:" + mEnableLines + " monthDaySize:"
				+ monthDaySize + " nextLines:" + nextLines);
		return firstDayindex;
	}

	public MonthItem getMonthShowItem(int position, int row)// row form 1---7
	{
		Log.i(TAG, "[getMonthShowItem]position:" + position + " row:" + row);
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
		} else if (position > (mEnableLines - 1)) {
			// next month
			int day = row + position * Tool.WEEK_DAY_LENGTH - firstDayindex
					- monthDaySize+1;
			item.index = row;
			item.mDay = String.valueOf(day);
			item.mEnable = false;
		}
		Log.i(TAG, "[getMonthShowItem]Levae item.mDay:" + item.mDay);
		return item;
	}
}
