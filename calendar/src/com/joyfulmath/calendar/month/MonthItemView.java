package com.joyfulmath.calendar.month;

import java.util.ArrayList;
import java.util.List;

import com.joyfulmath.calendar.CalendarControl;
import com.joyfulmath.calendar.util.Tool;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MonthItemView extends View {

	public static final int MAX_LINES_OF_MONTH = 6;
	public static final int CURRENT_SIZE_DIFF = 10;
	private static final String TAG = "calendar.MonthItemView";
	private int mIndex  =-1;
	private CalendarControl mControl = null;
	private List<Integer> mString = new ArrayList<Integer>();
	private Paint mShowPaint = null;
	private Paint mHPaint = null;
	private Paint mRBPaint = null;
	private Paint mLinePaint = null;
	
	public MonthItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setWillNotDraw(false);
		Log.i(TAG, "[MonthItemView] constructor");
		
		mControl = CalendarControl.getinstance();
		mString.clear();
		mShowPaint = new Paint();
		mShowPaint.setColor(Color.BLACK);
		mShowPaint.setTextSize(28);
		
		mRBPaint = new Paint();
		mRBPaint.setColor(Color.argb(0xaa, 0x00, 0x00, 0xff));
		
		mHPaint = new Paint();
		mHPaint.setColor(Color.GRAY);
		mHPaint.setTextSize(28);
		
		mLinePaint = new Paint();
		mLinePaint.setColor(Color.argb(0xff, 0x57, 0x57, 0x57));
		mLinePaint.setStrokeWidth(Tool.CALENDAR_CELL_DIFF);
		Log.i(TAG, "[MonthItemView] constructor CALENDAR_CELL_WIDTH:"+Tool.CALENDAR_CELL_WIDTH);
	}

	public MonthItemView(Context context) {
		super(context);

	}
	
	public void setLines(int position,int screenWidth,int screenHeight)
	{
		mIndex = position;
		this.invalidate();
		Log.i(TAG, "[setLines]mLine:"+mIndex);
	}

	public void setLines(int position)
	{
		mIndex = position;
		Log.i(TAG, "[setLines]mLine:"+mIndex);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		Log.i(TAG, "[onDraw]mIndex:"+mIndex);
		super.onDraw(canvas);
		canvas.save();
		for(int i=1;i<=Tool.WEEK_DAY_LENGTH;i++)
		{
			MonthItem item = mControl.getMonthShowItem(mIndex, i);
			canvas.save();
			int left = (i-1)*(Tool.CALENDAR_CELL_WIDTH+Tool.CALENDAR_CELL_DIFF);
			int top = 0*Tool.CALENDAR_CELL_HEIGHT;
			int right = left+Tool.CALENDAR_CELL_WIDTH;
			int bottom = top+Tool.CALENDAR_CELL_HEIGHT;
	
			if(item.current)
			{
				canvas.save();
				Region region = new Region();
				int left2 = left + CURRENT_SIZE_DIFF;
				int right2 = right - CURRENT_SIZE_DIFF;
				int top2 = top + CURRENT_SIZE_DIFF;
				int bottom2 = bottom - CURRENT_SIZE_DIFF;
				region.op(new Rect(0,0,right*2,bottom*2), Region.Op.DIFFERENCE);
//				region.op(new Rect(left2,top2,right2,bottom2), Region.Op.UNION);
				Log.i(TAG, "[onDraw]left:"+left+"\t top:"+top+"\t right:"+right+"\t bottom:"+bottom);
				Log.i(TAG, "[onDraw]left2:"+left2+"\t top2:"+top2+"\t right2:"+right2+"\t bottom2:"+bottom2);
				canvas.clipRegion(region);
				canvas.drawColor(Color.BLUE);
//				canvas.drawRect(left, top, right, bottom, mRBPaint);
				canvas.restore();
			}

			if(item.mEnable)
			{
				canvas.drawText(item.mDay, left+20, top+30, mShowPaint);
			}
			else
			{
				canvas.drawText(item.mDay, left+20, top+30, mHPaint);
			}
			
			canvas.drawLine(right, top, right, bottom, mLinePaint);
			
			canvas.restore();
		}
				
		canvas.restore();
		Log.i(TAG, "[onDraw] leave");
	}

	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		this.setMeasuredDimension(Tool.mScreenWidth, Tool.CALENDAR_CELL_HEIGHT);
	}
		

	
}
