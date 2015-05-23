package com.example.drawmenu3;

import android.content.Context;
import android.graphics.AvoidXfermode.Mode;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.graphics.Xfermode;



public class Draw4 extends View {
	
	//参数设定
	public float preX;
	public float preY;
	public float currentX;
	public float currentY;
	public boolean Eraser;
	
	private int View_Width;
	private int View_height;
	
	//创建一个缓冲区域,画布，画笔，画图路径
	Bitmap cacheBitmap=null;
	Canvas cachecanvas=null;
	public Paint p=null;
	private Path path=null;

	
	public Draw4(Context context) {
		super(context);
		
	}
	
	public Draw4(Context context, AttributeSet set) {
		super(context,set);
		
		//获取屏幕宽度高度作为画布宽高。
		WindowManager wm = (WindowManager) getContext().getSystemService(  
                Context.WINDOW_SERVICE);  
		View_Width= wm.getDefaultDisplay().getWidth();
		View_height= wm.getDefaultDisplay().getHeight();  
		
		//初始化画布
		cacheBitmap=Bitmap.createBitmap(View_Width, View_height, Config.ARGB_8888);
		cachecanvas=new Canvas();
		cachecanvas.setBitmap(cacheBitmap);
		cachecanvas.drawColor(Color.TRANSPARENT);  //画布背景颜色
		
		//初始化路径
		path=new Path();
		
		//设置画笔信息
		setPaint(Color.BLACK,5); 
		
		// TODO Auto-generated constructor stub
	}
	
	//初始化画笔风格,传入颜色，画笔大小等参数
	public void setPaint(int color,int strokeWidth){  
     
        p = new Paint(Paint.DITHER_FLAG);  
        p.setColor(color);  
        // 设置画笔风格  
        p.setStyle(Paint.Style.STROKE);  
        p.setStrokeWidth(strokeWidth);  
        // 反锯齿  
        p.setAntiAlias(true);  
        p.setDither(true);  
    }  
	
	
	public boolean onTouchEvent(MotionEvent event)
	{
		
		// 修改currentX、currentY两个属性
		currentX=event.getX();
		currentY=event.getY();
		
		//根据条件实现橡皮擦功能
		if(Eraser==true){
			p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN)); 
			switch (event.getAction()) {  
	        case MotionEvent.ACTION_DOWN:  
	  	        path.moveTo(currentX,currentY);  
	            preX = currentX;  
	            preY = currentY;  
	            break;  
	        case MotionEvent.ACTION_MOVE:   
	            path.quadTo(preX, preY,currentX,currentY);  
	            preX = currentX;  
	            preY = currentY;  
	            cachecanvas.drawPath(path, p); 
	            break;  
	        case MotionEvent.ACTION_UP:  
	            cachecanvas.drawPath(path, p);
	            path.reset(); 
	            break;  
	        }  
			
			
		}
		// 通知当前组件重绘自己
		invalidate();
		// 返回true表明该处理方法已经处理该事件
		return true;
	}
	
	
	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		Paint bmp=new Paint();
		canvas.drawBitmap(cacheBitmap, 0, 0, bmp);
		
	}

/*	public void drawcircle(){
		
		Eraser=false;
		cachecanvas.drawCircle(currentX, currentY,40, p);
		//invalidate();
	}
	
	public void drawline(){
		
		Eraser=false;
		Path path=new Path();
		path.moveTo(currentX, currentY);
		path.lineTo(currentX+60,currentY +40);
		cachecanvas.drawPath(path, p);
	}
	
	public void drawtriggle(){
		
		Eraser=false;
		Path path=new Path();
		path.moveTo(currentX, currentY);
		path.lineTo(currentX+30,currentY +40);
		path.lineTo(currentX-50, currentY+60);
		path.close();
		cachecanvas.drawPath(path, p);
	}
	

	public void drawrect(){
		
		Eraser=false;
		cachecanvas.drawRect(currentX, currentY, currentX+60, currentY+40, p);
	}
	
	public void drawsquare(){
		
		Eraser=false;
		cachecanvas.drawRect(currentX+50, currentY, currentX, currentY+50, p);
		//hecanvas.drawRect(currentX, currentY, currentX+50 currentY+50, p);
	}
	
	public void eraser(){
		
		Eraser=true;
			
	}
	*/
	
	public void testGithub(){
		Log.d("GitHub", "This method just for test github system");
	}
	
	public void SetColor(int C){
		
		Eraser=false;
		switch(C){
		
		case 1:
			p.setColor(Color.RED);
			break;
		case 3:
			p.setColor(Color.GREEN);
			break;
		case 2:
			p.setColor(Color.BLUE);
			break;
		}
		
	}
	
	public void SetDraw(int D){
		
		Eraser=false;
		
		switch(D){
		
		case 1:
			//画圆
			cachecanvas.drawRect(currentX, currentY, currentX+60, currentY+40, p);
			break;
		case 3:
			//画直线
			Path path=new Path();
			path.moveTo(currentX, currentY);
			path.lineTo(currentX+60,currentY +40);
			cachecanvas.drawPath(path, p);
			break;
		case 2:
			//画矩形
			cachecanvas.drawRect(currentX, currentY, currentX+60, currentY+40, p);
			break;
		case 4:
			//
			Path path1=new Path();
			path1.moveTo(currentX, currentY);
			path1.lineTo(currentX+30,currentY +40);
			path1.lineTo(currentX-50, currentY+60);
			path1.close();
			cachecanvas.drawPath(path1, p);
			break;
		case 5:
			cachecanvas.drawRect(currentX+50, currentY, currentX, currentY+50, p);
			break;
		
		}
		
		
	}
	
	

}
