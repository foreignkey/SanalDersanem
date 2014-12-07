package com.softwareG06.sanaldersanem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OynatmaListesiAl extends Activity{

	String kurs,ders,playlist,add;
	TextView tv;
	 Bundle veriAl  = new  Bundle();
	 
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
		setContentView(R.layout.oynatma_listesi_al);
		veriAl = getIntent().getExtras();
		kurs    = veriAl.getString("kurs");
		ders=veriAl.getString("ders");
		playlist=veriAl.getString("playlist");
		
		tv=(TextView)findViewById(R.id.textView1);
		
		tv.setText("ders : "+ders+" kurs : "+kurs+" playlist : "+playlist);
		
		try {
			Class.forName(driver).newInstance();
	        conn = DriverManager.getConnection(url+dbName,userName,password);
	        stmt = conn.createStatement();
	        add= "INSERT INTO playlist_al (kurs, ders, playlist) VALUES (?,?,?)"; 
	        ps=(PreparedStatement) conn.prepareStatement(add);
	        ps.setString(1,kurs);
	        ps.setString(2,ders);
	        ps.setString(3,playlist);
	        ps.execute();
	       
	       
	        }
	        catch(Exception e){
	        	
	        }
		
	}
	
	
}
