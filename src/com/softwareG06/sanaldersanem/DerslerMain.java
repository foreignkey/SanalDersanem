package com.softwareG06.sanaldersanem;

import org.aynsoft.playList.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class DerslerMain extends Activity {
	ImageButton img;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dersler);
		
		
		ImageButton img=(ImageButton)findViewById(R.id.imgButton2);
    }
    public void clickHandler(View v)
    {
    switch(v.getId())
    {
    case R.id.imgButton1:
    	Toast.makeText(getApplicationContext(), "Video", Toast.LENGTH_LONG).show();
    	break;
    case R.id.imgButton2:
    	Toast.makeText(getApplicationContext(), "Denemeler",Toast.LENGTH_LONG).show();
    	break;
    	
    
    }
		
	}
}
