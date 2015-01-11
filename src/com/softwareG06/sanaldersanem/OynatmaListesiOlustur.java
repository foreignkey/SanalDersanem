package com.softwareG06.sanaldersanem;

import java.sql.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
 
public class OynatmaListesiOlustur extends Activity {
 
  private Spinner spinner1, spinner2,s,s2,s3;
  private Button btnSubmit;
  ArrayAdapter adapter,adapter2,adapter3;
  
  String playlist,uname;
  Bundle veriAl  = new  Bundle();
  EditText play;
  
  TextView hos;
  
	 private Connection conn = null;  
		private String url = "jdbc:mysql://85.159.67.247:3306/";
		private String dbName = "softwareG06";
		private String driver = "com.mysql.jdbc.Driver";  
		private String userName = "softwareG06"; 
		private String password = "softwareG06";
		
		Statement stmt;
		ResultSet rsSpiner;
		ResultSetMetaData rsmdSpiner ;
		PreparedStatement ps;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.oynatma_listeni_ekle);
	
	hos=(TextView)findViewById(R.id.txtHos);
	veriAl = getIntent().getExtras();
	uname    = veriAl.getString("uname");
	
	hos.setText("Hoþgeldiniz "+uname+"!");
	
	String[] kursSec = new String[] { "KPSS", "YGS", "LYS" };
	String[] ygs = new String[] { "Türkçe", "Matematik-1", "Geometri-1", "Fizik-1","Kimya-1", "Biyoloji", "Tarih", "Coðrafya" };
	String[] lys = new String[] { "Türkçe", "Matematik-2", "Geometri-2", "Fizik-2","Kimya-2", "Biyoloji", };
	String[] kpss = new String[] { "Türkçe", "Matematik", "Geometri", "Vatandaþlýk","Coðrafya", "Tarih", "Güncel Konular", "Geliþim Psikolojisi", "Öðrenme Psikolojisi","Ölçme ve Deðerlendirme", "Program Geliþtirme", "Öðretim Yöntem ve Teknikleri", "Rehberlik" };
	 
	//addItemsOnSpinner2();
	spinner1 = (Spinner) findViewById(R.id.spinner1);
	spinner2 = (Spinner) findViewById(R.id.spinner2);
	s = (Spinner) findViewById(R.id.spinner1);
	s2 = (Spinner) findViewById(R.id.spinner2);
	
	adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item, kpss);
	adapter2=new ArrayAdapter(this,android.R.layout.simple_spinner_item, ygs);
	adapter3=new ArrayAdapter(this,android.R.layout.simple_spinner_item, lys);

	s.setAdapter(new ArrayAdapter(this,android.R.layout.simple_spinner_item, kursSec));

	s.setOnItemSelectedListener(new OnItemSelectedListener(){

	@Override
	public void onItemSelected(AdapterView parent, View v,
	int pos, long id) {
	if(pos==0){
		s2.setAdapter(adapter);
	
	}
	else if (pos==1) {
		s2.setAdapter(adapter2);
	}
	else if (pos==2) {
		s2.setAdapter(adapter3);
	}
	}

	@Override
	public void onNothingSelected(AdapterView arg0) {

	}});
	
	
	
	
	btnSubmit = (Button) findViewById(R.id.btnEkle);
 
       
	btnSubmit.setOnClickListener(new OnClickListener() {
 
	  @Override
	  public void onClick(View v) {
		  play=(EditText)findViewById(R.id.editText1);
			playlist=play.getText().toString();
			
			
			
		/*  Intent i=new Intent(OynatmaListesiOlustur.this,OynatmaListesiAl.class);
		  i.putExtra("kurs", String.valueOf(spinner1.getSelectedItem()));
		  i.putExtra("ders", String.valueOf(spinner2.getSelectedItem()));
		  i.putExtra("playlist", playlist);
		  startActivity(i);*/
			
			try {
				Class.forName(driver).newInstance();
		        conn = DriverManager.getConnection(url+dbName,userName,password);
		        stmt = conn.createStatement();
		       String add= "INSERT INTO playlist_al (kurs, ders, playlist,uname) VALUES (?,?,?,?)"; 
		        ps=(PreparedStatement) conn.prepareStatement(add);
		        ps.setString(1, String.valueOf(spinner1.getSelectedItem()));
		        ps.setString(2,String.valueOf(spinner2.getSelectedItem()));
		        ps.setString(3,playlist);
		        ps.setString(4,uname);
		        ps.execute();
		       
		       
		        }
		        catch(Exception e){
		        	
		        }
	    Toast.makeText(OynatmaListesiOlustur.this,
		"Oynatma Listesi Oluþturuldu : " + 
                "\nKurs : "+ String.valueOf(spinner1.getSelectedItem()) + 
                "\nDers : "+ String.valueOf(spinner2.getSelectedItem())+" playlist "+playlist,
			Toast.LENGTH_SHORT).show();
	    
	    
	  }
 
	});	spinner1 = (Spinner) findViewById(R.id.spinner1);
	  }
 
  
 
  
}