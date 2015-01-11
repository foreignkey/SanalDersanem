package com.softwareG06.sanaldersanem;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MakeChoice extends Activity{
	TextView logout;
	Button start,back;

	static String user,name;
	Bundle veriAl=new Bundle();
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.in, R.anim.out);
		setContentView(R.layout.makechoice);
		
		veriAl = getIntent().getExtras();
		user    = veriAl.getString("uname");
		name   = veriAl.getString("name");
		
		start=(Button)findViewById(R.id.StartBtn);
		
		Spinner LanguageSpinner=(Spinner)findViewById(R.id.languagespinner);
		ArrayAdapter<CharSequence> AdapterLanguage=ArrayAdapter.createFromResource(this,R.array.KursSec,android.R.layout.simple_spinner_item);
		AdapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		LanguageSpinner.setAdapter(AdapterLanguage);

		
	
		Spinner LevelSpinner=(Spinner)findViewById(R.id.levelspinner);
		ArrayAdapter<CharSequence> AdapterLevel=ArrayAdapter.createFromResource(this,R.array.ListeLevel,android.R.layout.simple_spinner_item);
		AdapterLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		LevelSpinner.setAdapter(AdapterLevel);


		Spinner StrategySpinner=(Spinner)findViewById(R.id.stratygyspinner);
		ArrayAdapter<CharSequence> AdapterStrategy=ArrayAdapter.createFromResource(this,R.array.Listestrategy,android.R.layout.simple_spinner_item);
		AdapterStrategy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		StrategySpinner.setAdapter(AdapterStrategy);

		
		start.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),TestCoz.class);
				
				startActivity(intent);

			}
		});
		
		/*back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(MakeChoice.this, Giris.class);
				i.putExtra("uname",user);
				i.putExtra("name",name);
				startActivity(i);

			}
		});*/
	

	}
	
}