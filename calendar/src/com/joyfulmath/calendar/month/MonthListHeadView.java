package com.joyfulmath.calendar.month;

import com.joyfulmath.calendar.util.Tool;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MonthListHeadView extends View {

	private Paint mTPaint = null;
	private Paint mBKPaint = null;
	private String[] mWeeks = {
			"SUN",
			"MON",
			"TUE",
			"WED",
			"THU",
			"FRI",
			"SAT"
	};
	
	public MonthListHeadView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}	
	
	public MonthListHeadView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		mTPaint = new Paint();
		mTPaint.setColor(Color.argb(0xff, 0x57, 0x57, 0x57));
		mTPaint.setTextSize(38);
		
		mBKPaint = new Paint();
		mBKPaint.setColor(Color.argb(0xff, 0xA2, 0xB5, 0xCD));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		canvas.drawRect(0, 0, Tool.mScreenWidth, Tool.CALENDAR_CELL_HEIGHT/2, mBKPaint);
		
		for(int i=1;i<=Tool.WEEK_DAY_LENGTH;i++)
		{
			canvas.save();
			int left = (i-1)*(Tool.CALENDAR_CELL_WIDTH+Tool.CALENDAR_CELL_DIFF);
			int top = 0*Tool.CALENDAR_CELL_HEIGHT;
//			int right = left+Tool.CALENDAR_CELL_WIDTH;
//			int bottom = top+Tool.CALENDAR_CELL_HEIGHT;
			
			float textwidth = mTPaint.measureText(mWeeks[i-1]);
			
			int startdiff = (int) ((Tool.CALENDAR_CELL_WIDTH-textwidth)/2);
			
			canvas.drawText(mWeeks[i-1], left+startdiff, top+Tool.CALENDAR_CELL_HEIGHT/4, mTPaint);
			
			canvas.restore();
		}
		
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		this.setMeasuredDimension(Tool.mScreenWidth, Tool.CALENDAR_CELL_HEIGHT/2);
	}
	
	
	
}
