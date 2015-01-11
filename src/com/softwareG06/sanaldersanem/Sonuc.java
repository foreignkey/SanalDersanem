package com.softwareG06.sanaldersanem;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.softwareG06.sanaldersanem.DomParser.XMLDOMParser;


import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Sonuc extends Activity{
	TextView correctanswer,wronganswer,percentage,time,number,question,correct,textview,your;
	ImageView imageView;
	TableLayout tablayout;
	LinearLayout linearelayout;
	TableRow tablerow;
	Button restart,logout;

	ArrayList<String> AllcorrectAnswer,AllAnswer,Allquestion,Allcorrectanswer;
	ArrayList<TextView> textviewlist;
	String score,timetext,noq,valuequestion,thecorrect;
	Long m,n;
	int numberofquestion;
	int q=0;
	int som=0;
	boolean etat=true;
	int index=0;
	int tab[]={0,5,10,15};
	AlertDialog.Builder Alert;
	OnClickListener textclick;
	XMLDOMParser parser;
	NodeList ListnodeAnswer;
	static final String NODE_QG = "questiongroup";
	static final String NODE_Q = "question";
	static final String NODE_QT = "questiontype";
	static final String NODE_QN = "questionnumber";
	static final String NODE_A ="answer";

	LayoutInflater factory;
	View alertDialogView;

	public void timeoflpi(){
		String[] Pause_time=timetext.split(" : ");
		m=Long.parseLong(Pause_time[0].trim());
		n=Long.parseLong(Pause_time[1].trim());
		m=(m*60)+n;
		m=m*1000;
		Long mills=1800000-m;
		long seconds=mills/1000;
		long minutes=seconds/60;
		seconds = seconds % 60;
		minutes = minutes % 60;
		time.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));	
	}



	public void Parser(){
		parser = new XMLDOMParser();
		AssetManager manager = getAssets();
		InputStream stream;

		try {
			stream = manager.open("questions.xml");
			Document doc = parser.getDocument(stream);
			NodeList nodeList = doc.getElementsByTagName("questiongroup");

			Element e = (Element) nodeList.item(som-1);
			ListnodeAnswer=e.getElementsByTagName("answer");

			valuequestion=parser.getValue(e, NODE_Q);
			Allquestion.add(valuequestion);


		}


		catch (IOException e1) {
			e1.printStackTrace();
		}

		for(int i=0;i<ListnodeAnswer.getLength();i++){
			Node node=ListnodeAnswer.item(i);
			if(node.hasAttributes()){
				thecorrect=parser.getTextNodeValue(node);
				Allcorrectanswer.add(thecorrect);

			}
		}
	}

	public void Dialog(String valuequestion,String thecorrect,String yoour){

		question.setText(valuequestion);
		correct.setText(thecorrect);
		your.setText(yoour);


	}



	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.sonuc);



		AllcorrectAnswer=new ArrayList<String>();
		AllAnswer=new ArrayList<String>();
		Allcorrectanswer=new ArrayList<String>();
		Allquestion=new ArrayList<String>();
		textviewlist=new ArrayList<TextView>();


		correctanswer=(TextView)findViewById(R.id.numberofcorrectanswer);
		wronganswer=(TextView)findViewById(R.id.numberofwronganswer);
		percentage=(TextView)findViewById(R.id.precentageofcorrects);
		imageView=(ImageView)findViewById(R.id.imageView1);
		linearelayout=(LinearLayout)findViewById(R.id.LinearLayout2);
		restart=(Button)findViewById(R.id.button1);
		time=(TextView)findViewById(R.id.time);





		Intent intent=getIntent();

		score=intent.getStringExtra("score");
		correctanswer.setText(score);

		AllcorrectAnswer=intent.getStringArrayListExtra("allcorrectanswer");
		AllAnswer=intent.getStringArrayListExtra("allanswer");

		timetext=intent.getStringExtra("time");
		noq=intent.getStringExtra("numberofquestion");
		numberofquestion=Integer.parseInt(noq);






		int x=Integer.parseInt(score);
		double y=Double.parseDouble(score);
		double per=(y/numberofquestion)*100;

		timeoflpi();
		percentage.setText(String.valueOf(per)+"%");
		wronganswer.setText(String.valueOf(numberofquestion-x));



		tablayout=new TableLayout(this);


		for(int i=1;i<(numberofquestion/4);i++) {

			Resources resource = this.getResources();
			tablerow=new TableRow(this);
			tablerow.setBackgroundColor(resource.getColor(R.color.red));	


			for(int j=1;j<=(numberofquestion/4);j++) {

				som=j+tab[index];

				TextView number=new TextView(this) {

					protected void onDraw(Canvas canvas) {
						super.onDraw(canvas);
						Rect rect = new Rect();
						Paint paint = new Paint();
						paint.setStyle(Paint.Style.STROKE);
						paint.setColor(Color.WHITE);
						paint.setStrokeWidth(2);
						getLocalVisibleRect(rect);
						canvas.drawRect(rect, paint);

					}

				};

				Parser();


				TableRow.LayoutParams trParams = new TableRow.LayoutParams(0, LayoutParams.FILL_PARENT, 10f);
				number.setText(""+(som));
				number.setPadding(6, 4, 6, 4);
				number.setTextColor(resource.getColor(R.color.white));
				number.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                number.setTag(som-1);
				textviewlist.add(number);



				for(int k=0;k<AllcorrectAnswer.size();k++){

					if((som)==Integer.parseInt((AllcorrectAnswer.get(k))))
						number.setBackgroundColor(resource.getColor(R.color.green));	
				}

				number.setLayoutParams(trParams);
				tablerow.addView(number);

			}
			index++;

			final LinearLayout.LayoutParams tableParams = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			tablayout.setLayoutParams(tableParams);

			tablayout.addView(tablerow);

		}


		linearelayout.addView(tablayout);

		Log.d("rdfvbbbjh", String.valueOf(Allquestion.size())+String.valueOf(Allcorrectanswer.size())+String.valueOf(AllAnswer.size()));
		
		
          
		for(final TextView textview : textviewlist){

			Alert=new AlertDialog.Builder(this);

			textview.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {

					
					for(int i=0;i<Allquestion.size();i++){
						
						if(v.getTag().toString().equals(String.valueOf(i))){
							
						
						factory=LayoutInflater.from(Sonuc.this);
						alertDialogView = factory.inflate(R.layout.alertdialog, null);
						
                     	Alert.setView(alertDialogView);

						question=(TextView)alertDialogView.findViewById(R.id.question);
						correct=(TextView)alertDialogView.findViewById(R.id.correct);
						your=(TextView)alertDialogView.findViewById(R.id.your);

						Alert.setTitle(R.string.result);
						Alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog, int which) {

								dialog.cancel();


							}
						});
						Alert.show();
						
						
							Dialog(Allquestion.get(i), Allcorrectanswer.get(i),AllAnswer.get(i));
							
						}	
				}
				}

			});

	}
		restart.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),MakeChoice.class);
				startActivity(intent);

			}
		});

		


	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			Intent intent=new Intent(getApplicationContext(),MakeChoice.class);
			startActivity(intent);

		}

		return super.onKeyDown(keyCode, event);
	}


}
