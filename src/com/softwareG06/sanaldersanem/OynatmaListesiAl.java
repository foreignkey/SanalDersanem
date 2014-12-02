package com.softwareG06.sanaldersanem;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OynatmaListesiAl extends Activity{

	String kurs,ders,playlist;
	TextView tv;
	 Bundle veriAl  = new  Bundle();
	 
	@Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oynatma_listesi_al);
		veriAl = getIntent().getExtras();
		kurs    = veriAl.getString("kurs");
		ders=veriAl.getString("ders");
		playlist=veriAl.getString("playlist");
		
		tv=(TextView)findViewById(R.id.textView1);
		
		tv.setText("ders : "+ders+" kurs : "+kurs+" playlist : "+playlist);
		
		
		
	}
}
