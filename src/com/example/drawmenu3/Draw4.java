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
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.graphics.Xfermode;



public class Draw4 extends View {
	
	//�����趨
	public float preX;
	public float preY;
	public float currentX;
	public float currentY;
	public boolean Eraser;
	
	private int View_Width;
	private int View_height;
	
	public int choose;
	
	//����һ����������,���������ʣ���ͼ·��
	Bitmap cacheBitmap=null;
	Canvas cachecanvas=null;
	public Paint p=null;
	private Path path=null;

	
	public Draw4(Context context) {
		super(context);
		
	}
	
	public Draw4(Context context, AttributeSet set) {
		super(context,set);
		
		//��ȡ��Ļ��ȸ߶���Ϊ������ߡ�
		WindowManager wm = (WindowManager) getContext().getSystemService(  
                Context.WINDOW_SERVICE);  
		View_Width= wm.getDefaultDisplay().getWidth();
		View_height= wm.getDefaultDisplay().getHeight();  
		
		//��ʼ������
		cacheBitmap=Bitmap.createBitmap(View_Width, View_height, Config.ARGB_8888);
		cachecanvas=new Canvas();
		cachecanvas.setBitmap(cacheBitmap);
		cachecanvas.drawColor(Color.WHITE);  //����������ɫ
		
		//��ʼ��·��
		path=new Path();
		
		//���û�����Ϣ
		setPaint(Color.BLACK,5); 
		
		// TODO Auto-generated constructor stub
	}
	
	//��ʼ�����ʷ��,������ɫ�����ʴ�С�Ȳ���
	public void setPaint(int color,int strokeWidth){  
     
        p = new Paint(Paint.DITHER_FLAG);  
        p.setColor(color);  
        // ���û��ʷ��  
        p.setStyle(Paint.Style.STROKE);  
        p.setStrokeWidth(strokeWidth);  
        // �����  
        p.setAntiAlias(true);  
        p.setDither(true);  
    }  
	
	
	public boolean onTouchEvent(MotionEvent event)
	{
		
		// �޸�currentX��currentY��������
		currentX=event.getX();
		currentY=event.getY();
	
		//��������ʵ����Ƥ������
		if(Eraser==true){
			setPaint(Color.WHITE,10);
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
				
		}else{
			
			switch (event.getAction()) {  
	        case MotionEvent.ACTION_DOWN:  
	  	        touch_start(currentX,currentY);
	            break;  
	        case MotionEvent.ACTION_MOVE:   
	        	 touch_move(currentX,currentY);   
	            break;  
	        case MotionEvent.ACTION_UP:  
	        	touch_up(); 
	            break;  
	        }  
			
			
		}
		
		// ֪ͨ��ǰ����ػ��Լ�
		invalidate();
		// ����true�����ô������Ѿ�������¼�
		return true;
	}
	
	
	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		Paint bmp=new Paint();
		canvas.drawBitmap(cacheBitmap, 0, 0, bmp);
		
	}
	
	//���û�����ɫ
	public void SetColor(int Choose){
		
		Eraser=false;
		switch(Choose){
		
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

	/*
	public void SetDraw(int Choose){
		
		Eraser=false;
		
		switch(Choose){
		
		case 4:
			//��Բ
			cachecanvas.drawCircle(currentX, currentY,40, p);
			break;
		case 7:
			//��ֱ��
			Path path=new Path();
			path.moveTo(currentX, currentY);
			path.lineTo(currentX+60,currentY +40);
			cachecanvas.drawPath(path, p);
			break;
		case 5:
			//������
			cachecanvas.drawRect(currentX, currentY, currentX+60, currentY+40, p);
			break;
		case 8:
			//��������
			Path path1=new Path();
			path1.moveTo(currentX, currentY);
			path1.lineTo(currentX+30,currentY +40);
			path1.lineTo(currentX-50, currentY+60);
			path1.close();
			cachecanvas.drawPath(path1, p);
			break;
		case 6:
			cachecanvas.drawRect(currentX+50, currentY, currentX, currentY+50, p);
			break;
		
		}
	}*/
	
	//ÿ�λ�ͼ�����·��,�ƶ�����㴦������¼���
	public void touch_start(float x,float y){
		x=currentX;
		y=currentY;
		path.reset();   
		path.moveTo(x, y);
		preX=x;
		preY=y;
		
	}
	
	public void touch_move(float x,float y){
		
		x=currentX;
		y=currentY;
		
		switch(choose){
		
		case 4:
			//��Բ
			path.reset();
			if(x>=preX){
				path.addCircle(preX, preY, (x-preX)/2, Path.Direction.CW);
				
				
			}else{
				path.addCircle(preX, preY, (preX-x)/2, Path.Direction.CW);
				
			}
			
		case 7:
			//��ֱ��
			path.reset();
			path.moveTo(preX, preY);
			path.lineTo(x,y);
			//cachecanvas.drawPath(path, p);
			break;
			
		case 5:
			//������
			path.reset();		
			RectF rect1=new RectF();
			rect1.set(preX, preY, x,y);
			path.addRect(rect1, Path.Direction.CW);
			break;
			
		/*case 8:
			//��������
			path.reset();
			
			RectF rect2=new RectF();
			
			
			//rect1.set(left, top, right, bottom);
			path.addPath(path, currentX, currentY);
			path.arcTo(rect2, x, y);
			path.addPath(path, currentX, currentY);
			
			
			Path path1=new Path();
			path1.moveTo(currentX, currentY);
			path1.lineTo(currentX+30,currentY +40);
			path1.lineTo(currentX-50, currentY+60);
			path1.close();
			cachecanvas.drawPath(path1, p);
			break;*/
			
		case 6:
			//��������
			path.reset();
			RectF rect3=new RectF();
			if(x>=preX){
				rect3.set(preX, preY, x, preY+x-preX);
				
			}else{
				rect3.set(preX, preY, x, preY+preX-x);
				
			}
			path.addRect(rect3, Path.Direction.CW);
			break;
		
		}
		
	}

	//�յ㴦����Path���Ƶ�������
	public void touch_up(){
		cachecanvas.drawBitmap(cacheBitmap, 0, 0, p);
		path.reset();
	}
	
	

}
