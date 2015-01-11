package com.softwareG06.sanaldersanem;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import com.softwareG06.sanaldersanem.DomParser.XMLDOMParser;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class TestCoz extends Activity  {
	TextView numeroText,valueText,timertextvalue;
	RadioGroup rg;
	RadioButton rb,cheked;
	ImageView back,next,cancel,pause;
	View v;

	int numberofanswer=0;
	int score=0;
	long m,n;
	Integer ActtuelQuestion=0;
	int timervalue=1800000;
	String correctAnswer, chekedAnswer;
	ArrayList<String> Allcorrectanswer=new ArrayList<String>();
	ArrayList<String> Allanswer=new ArrayList<String>();
	ArrayList<integer> Allcheked=new ArrayList<integer>();
	NodeList ListnodeAnswer;
	CountDownTimer chronodown;
	XMLDOMParser parser;

	static final String NODE_QG = "questiongroup";
	static final String NODE_Q = "question";
	static final String NODE_QT = "questiontype";
	static final String NODE_QN = "questionnumber";
	static final String NODE_A ="answer";

	public void pause(){
		try{
			String s_time=null;

			s_time=timertextvalue.getText().toString();
			chronodown.cancel();
			String[] Pause_time=s_time.split(" : ");
			Log.d(Pause_time[0], Pause_time[1]);
			m=Long.parseLong(Pause_time[0].trim());
			n=Long.parseLong(Pause_time[1].trim());
			m=(m*60)+n;
			m=m*1000;

		}
		catch(Exception e){

		}
	}
	public String formatTime(long mills){
		String output="00:00";
		long seconds=mills/1000;
		long minutes=seconds/60;
		seconds = seconds % 60;
		minutes = minutes % 60;
		String sec=String.valueOf(seconds);
		String min=String.valueOf(minutes);

		if(seconds<10)
			sec = "0" +seconds;
		if(minutes<10)
			min="0" +minutes;

		output=min + " : " + sec;
		return output;

	}
	public void time(long m){
		chronodown=new CountDownTimer(m, 1000) {

			@Override
			public void onFinish() {


			}

			@Override
			public void onTick(long millisUntilFinished) {
				timertextvalue=(TextView)findViewById(R.id.timertextvalue);
				timertextvalue.setText(formatTime(millisUntilFinished));
			}
		}.start();   

	}


	public void ParserAvant(){
		parser = new XMLDOMParser();
		AssetManager manager = getAssets();
		InputStream stream;

		try {
			stream = manager.open("questions.xml");
			Document doc = parser.getDocument(stream);
			NodeList nodeList = doc.getElementsByTagName("questiongroup");

			Element e = (Element) nodeList.item(ActtuelQuestion);
			ListnodeAnswer=e.getElementsByTagName("answer");
			numberofanswer=ListnodeAnswer.getLength();
			numeroText.setText(""+Integer.parseInt(parser.getValue(e, NODE_QN)));
			valueText.setText(""+parser.getValue(e, NODE_Q));

		}

		catch (IOException e1) {
			e1.printStackTrace();
		}
		rg.removeAllViews();

		for(int i=0;i<numberofanswer;i++){
			rb=new RadioButton(this);
			rb.setText(parser.getTextNodeValue(ListnodeAnswer.item(i)));
			rb.setId(i);
			rg.addView(rb,i);
		}

	}


	public int ScoreAvant(){
		int x=0;

		for(int i=0;i<ListnodeAnswer.getLength();i++){
			Node node=ListnodeAnswer.item(i);
			if(node.hasAttributes()){
				correctAnswer=parser.getTextNodeValue(node);
			}
		}
		
		//if(rg.getCheckedRadioButtonId()!=-1){

			cheked=(RadioButton)findViewById(rg.getCheckedRadioButtonId());
			
			if(cheked.isChecked()){

				chekedAnswer =(String) cheked.getText();
				Allanswer.add(chekedAnswer);


				if(correctAnswer.equals(chekedAnswer)){
					Allcorrectanswer.add(String.valueOf(ActtuelQuestion+1));

					x=1;
				}

			}
		//}
		else 
		{
			Allanswer.add("No Answer");
			x=0;
		}



		return x;



	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_coz);

		numeroText=(TextView)findViewById(R.id.numberofquestion);
		valueText=(TextView)findViewById(R.id.questionText);
		rg=(RadioGroup)findViewById(R.id.radioGroup1);
		back=(ImageView)findViewById(R.id.imgback);    
		next=(ImageView)findViewById(R.id.imagnext);
		pause=(ImageView)findViewById(R.id.imagpause);
		cancel=(ImageView)findViewById(R.id.imagcancel);

		ParserAvant();


		time(timervalue);

		back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if(ActtuelQuestion>0){
					back.setEnabled(true);
					ActtuelQuestion=ActtuelQuestion-1;
					ParserAvant();

				}

			}
		});
		next.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				score=score+ScoreAvant();


				if(Integer.parseInt(numeroText.getText().toString())==20 | (timertextvalue.getText().toString()).equals("00 : 01")){

					Intent intent=new Intent(getApplicationContext(),Sonuc.class);

					intent.putExtra("score", String.valueOf(score));
					intent.putExtra("time", timertextvalue.getText().toString());
					intent.putExtra("numberofquestion", String.valueOf(ActtuelQuestion+1));
					intent.putStringArrayListExtra("allcorrectanswer",  Allcorrectanswer);
					intent.putStringArrayListExtra("allanswer",  Allanswer);
					startActivity(intent);
				}
				else {
					ActtuelQuestion=ActtuelQuestion+1;
					ParserAvant();

				}

			}
		});

		pause.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				pause();


				AlertDialog pauseAlert=new AlertDialog.Builder(TestCoz.this).create();
				pauseAlert.setTitle(R.string.Paused);
				pauseAlert.setMessage("Click on Button to close");
				pauseAlert.setIcon(R.drawable.ic_pause);
				pauseAlert.setButton("continue", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {

						time(m);
						dialog.cancel();


					}
				});
				pauseAlert.show();

			}
		});
		cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				pause();
				AlertDialog cancelAlert=new AlertDialog.Builder(TestCoz.this).create();
				cancelAlert.setTitle(R.string.Exit);
				cancelAlert.setMessage("Do you want to exit of the questionnaire");
				cancelAlert.setIcon(R.drawable.ic_cancel);

				cancelAlert.setButton("Yes", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						Intent intent=new Intent(getApplicationContext(),MakeChoice.class);
						startActivity(intent);

					}
				});

				cancelAlert.setButton2("No", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						time(m);
						dialog.cancel();

					}
				});
				cancelAlert.show();
			}
		});





	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:

			AlertDialog cancelAlert=new AlertDialog.Builder(TestCoz.this).create();
			cancelAlert.setTitle(R.string.Exit);
			cancelAlert.setMessage("Do you want to exit of the questionnaire");
			cancelAlert.setIcon(R.drawable.ic_cancel);

			cancelAlert.setButton("Yes", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					Intent intent=new Intent(getApplicationContext(),MakeChoice.class);
					startActivity(intent);

				}
			});

			cancelAlert.setButton2("No", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					time(m);
					dialog.cancel();

				}
			});
			cancelAlert.show();

		}

		return super.onKeyDown(keyCode, event);
	}

	
}


