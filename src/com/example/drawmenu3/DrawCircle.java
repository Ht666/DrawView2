package com.example.drawmenu3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawCircle extends View{

	public float currentX;
	public float currentY;
	
	final int View_Width=480;
	final int View_height=640;
	
	//����һ����������
	Bitmap cacheBitmap=null;
	Canvas cachecanvas=new Canvas();
	public Paint p=new Paint();
			
	public DrawCircle(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	
	public DrawCircle(Context context,AttributeSet set) {
		super(context,set);	
		cacheBitmap=Bitmap.createBitmap(View_Width, View_height, Config.ARGB_8888);
		//����cachecanvas������ͼ��������cacheBitmas��
		cachecanvas.setBitmap(cacheBitmap);
		//�����������ó�͸��ɫ
		cachecanvas.drawColor(Color.TRANSPARENT);
		//�������ʷ��
		p.setStyle(Paint.Style.STROKE);
		//���û�����ɫ
		p.setColor(Color.BLACK);
		//�����
		p.setAntiAlias(true);
	
	}
	
	
	// Ϊ������Ĵ����¼���д�¼�������
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		
		// �޸�currentX��currentY��������
		currentX=event.getX();
		currentY=event.getY();
		
		
		// ֪ͨ��ǰ����ػ��Լ�
		invalidate();
		// ����true�����ô������Ѿ�������¼�
		return true;
	}
	
	public void drawcircle(){
		cachecanvas.drawCircle(currentX, currentY,40, p);
		//invalidate();
	}
	
	public void drawline(){
		
		Path path=new Path();
		path.moveTo(currentX, currentY);
		path.lineTo(currentX+60,currentY +40);
		cachecanvas.drawPath(path, p);
	}
	
	public void drawtriggle(){
		Path path=new Path();
		path.moveTo(currentX, currentY);
		path.lineTo(currentX+30,currentY +40);
		path.lineTo(currentX-50, currentY+60);
		path.close();
		cachecanvas.drawPath(path, p);
	}
	

	public void drawrect(){
		
		cachecanvas.drawRect(currentX, currentY, currentX+60, currentY+40, p);
	}
	
	public void drawsquare(){
		
		cachecanvas.drawRect(currentX+50, currentY, currentX, currentY+50, p);
		//hecanvas.drawRect(currentX, currentY, currentX+50 currentY+50, p);
	}
	
	public void eraser(){
		
		p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		
		
	}
	
	
	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		Paint bmp=new Paint();
		canvas.drawBitmap(cacheBitmap, 0, 0, bmp);
		//canvas.drawCircle(currentX, currentY,20, p);
		// TODO Auto-generated constructor stub
		
		// ����һ��СԲ����ΪС��
		//canvas.drawCircle(currentX, currentY, 20, p);
		//canvas
	}

}
