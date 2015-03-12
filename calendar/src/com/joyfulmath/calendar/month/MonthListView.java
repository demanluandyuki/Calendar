package com.joyfulmath.calendar.month;

import com.joyfulmath.calendar.CalendarControl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ListView;

public class MonthListView extends ListView implements OnGestureListener {

	public static final int DIFF_X = 50;
	private static final String TAG = "calendar.MonthListView";
	GestureDetector mGestureDetector;
	Context mContext = null;
	int mMoveAction = -1;
	Handler mhanHandler = null;
	
	public MonthListView(Context context) {
		super(context);
		mContext= context;
		initView();
	}
	
	public MonthListView(Context context,AttributeSet attrs)
	{
		super(context, attrs);
		mContext= context;
		initView();
	}

	public void setHandle(Handler mhanHandler)
	{
		this.mhanHandler = mhanHandler;
	}
	
	private void initView()
	{
		Log.i(TAG, "[initView]");
		mGestureDetector = new GestureDetector(mContext,this);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		Log.i(TAG, "[onDown]");
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		Log.i(TAG, "[onFling]");
		float mDiff = e2.getX()-e1.getX();
		Log.i(TAG, "mDiff:"+mDiff);
		if(mDiff>=DIFF_X)
		{
			//right
			Message msg = mhanHandler.obtainMessage();
			msg.what = CalendarControl.MOVE_LAST_MONTH;
			mhanHandler.sendMessage(msg);
		}
		else if(mDiff<=(-DIFF_X))
		{
			//left
			Message msg = mhanHandler.obtainMessage();
			msg.what = CalendarControl.MOVE_NEXT_MONTH;
			mhanHandler.sendMessage(msg);
		}
		else
		{
			//ignore
			
		}
		
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		Log.i(TAG, "[onLongPress]");
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		Log.i(TAG, "[onScroll]");
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		Log.i(TAG, "[onShowPress]");
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		Log.i(TAG, "[onSingleTapUp]");
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		Log.i(TAG, "[onTouchEvent]");
		return mGestureDetector.onTouchEvent(ev);
	}
	
	
	
}
