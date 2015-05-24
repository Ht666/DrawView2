package com.example.drawmenu3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Menu3MainActivity extends Activity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu3_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu3_main, menu);
		getMenuInflater().inflate(R.menu.menu3,menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	//点击菜单选项，对调的方法
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//final DrawCircle draw = (DrawCircle) this.findViewById(R.id.draw);
		final Draw4 draw=(Draw4) this.findViewById(R.id.draw4);
		
		int choose;
		
		int id = item.getItemId();
		switch(id){
		
		case(R.id.font_check):
			item.setIntent(new Intent(this,Check_Activity.class));
			break;
		case(R.id.font_result):
			item.setIntent(new Intent(this,Result_Activity.class));
			break;
		
		//设置画笔颜色
		case(R.id.font_blue):
			choose=2;
			draw.SetColor(choose);
			break;
		case(R.id.font_green):
			choose=3;
			draw.SetColor(choose);
			break;
		case(R.id.font_red):
			choose=1;
			draw.SetColor(choose);
			break;
			//draw.onDraw(Canvas canvas);
			
		//根据所选图形画图
		case(R.id.font_circle):
			choose=4;
			//draw.SetDraw(choose);
			break;
		case(R.id.font_line):
			choose=7;
			//draw.SetDraw(choose);
			break;
		case(R.id.font_rect):
			choose=5;
			//draw.SetDraw(choose);
			break;
		case(R.id.font_square):
			choose=6;
			//draw.SetDraw(choose);
			break;
		case(R.id.font_trigger):
			choose=8;
			//draw.SetDraw(choose);
			break;
		case(R.id.font_eraser):
			draw.Eraser=true;
			break;
		
		}
		
		return super.onOptionsItemSelected(item);
	}
}



//tools:context="com.example.drawmenu3.DrawCirle"
