package com.softwareG06.sanaldersanem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.aynsoft.playList.R;

import com.touchmenotapps.widget.radialmenu.semicircularmenu.SemiCircularRadialMenu;
import com.touchmenotapps.widget.radialmenu.semicircularmenu.SemiCircularRadialMenuItem;
import com.touchmenotapps.widget.radialmenu.semicircularmenu.SemiCircularRadialMenuItem.OnSemiCircularRadialMenuPressed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {
	
	private SemiCircularRadialMenu mMenu;
	private SemiCircularRadialMenuItem mYGS, mLYS,  mKPSS;
	Button login,register;
	EditText kulAdi,sifre;
	
	private Connection conn = null;  
	private String url = "jdbc:mysql://85.159.67.247:3306/";
	private String dbName = "softwareG06";
	private String driver = "com.mysql.jdbc.Driver";  
	private String userName = "softwareG06"; 
	private String password = "softwareG06";  
	
	String kul,sif;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		login=(Button)findViewById(R.id.btnLogin);
		register=(Button)findViewById(R.id.btnUyeOl);
		
		kulAdi=(EditText)findViewById(R.id.edtKulAdi);
		sifre=(EditText)findViewById(R.id.edtSifre);
		
		
		
		 login.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					try {
						
						kul=kulAdi.getText().toString();
						sif=sifre.getText().toString();
						
				        Class.forName(driver).newInstance();
				        conn = DriverManager.getConnection(url+dbName,userName,password);
				        Toast.makeText(getApplicationContext(), "Connected to the database", Toast.LENGTH_SHORT).show();
				        String result = "Database connection success\n";
				        Statement stmt = conn.createStatement();
				        String sql = "INSERT INTO users VALUES ("+kul+",'"+sif+"')";
				        stmt.execute(sql);
				        
				     // stmt.executeUpdate(sql); 

				        conn.close();
				        Toast.makeText(getApplicationContext(), "Veri eklendi", Toast.LENGTH_SHORT).show();
				        System.out.println("Disconnected from database");
				    } catch (Exception e) {
				    	
				        e.printStackTrace();
				    }
		
		
				}});
		
	}
	
	
}
