package com.ultimate.text.editer.aa_front;

import com.ultimate.text.editer.aa.ebin.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class Activity_first_launcher extends Activity implements OnClickListener{

	ImageView imv_newtext, imv_list, imv_browse, imv_about;
	RelativeLayout relative_la_bg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_first_launcher);
		
		
//		relative_la_bg = (RelativeLayout) findViewById(R.id.relative_la_bg);
//		 SharedPreferences setting = getSharedPreferences("settings", 0); 
//		String  bg_colour_main = setting.getString("bg_colour_main", "g_black");          
//		Slsect_main_gridview_bg obb = new Slsect_main_gridview_bg();
//			 int n = obb.get_main_linear_layout_bg(bg_colour_main);
//			 relative_la_bg.setBackgroundDrawable(getResources().getDrawable(n)); //gradient all effect work
//		
		
		imv_newtext = (ImageView) findViewById(R.id.imageView1);
		imv_newtext.setOnClickListener(this);
		imv_newtext.setBackgroundResource(R.drawable.imv_toggle_newtext);
	      
		imv_list = (ImageView) findViewById(R.id.imageView2);
		imv_list.setOnClickListener(this);
		imv_list.setBackgroundResource(R.drawable.imv_toggle_list);
	      
		imv_browse = (ImageView) findViewById(R.id.imageView3);
		imv_browse.setOnClickListener(this);
		imv_browse.setBackgroundResource(R.drawable.imv_toggle_browse);
	      
	//	imv_about = (ImageView) findViewById(R.id.imageView4);
		//imv_about.setOnClickListener(this);
		//imv_about.setBackgroundResource(R.drawable.imv_toggle_about);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.imageView1:
		                              	imv_newtext.setBackgroundResource(R.drawable.imv_toggle_newtext);
			                              Intent in = new Intent("com.ultimate.text.editer.aa.pmTextEdit");
			                              startActivity(in);
			break;
		case R.id.imageView2:
			imv_browse.setBackgroundResource(R.drawable.imv_toggle_browse);
            Intent in2 = new Intent("com.ultimate.text.editer.aa_about_list_browse.Activity_list_files");
              startActivity(in2);
		                          
				                             
       break;
		case R.id.imageView3:
	     	imv_list.setBackgroundResource(R.drawable.imv_toggle_list);
            Intent in1 = new Intent("com.ultimate.text.editer.aa_about_list_browse.ActivityFileChooser");
               startActivity(in1);
			
       break;
	


		default:
			break;
		}
	}
}
