package com.joyfulmath.calendar.month;

public class MonthItem {
	
	public boolean mEnable;
	public boolean current;
	public boolean mHighlight;
	public int index;
	public String mDay;
	
	public MonthItem()
	{
		this.mEnable = false;
		this.current = false;
		this.mHighlight = false;
		this.index = -1;
		this.mDay = "NA";
	}
}
