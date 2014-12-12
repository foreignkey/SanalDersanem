package com.softwareG06.sanaldersanem;




import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


import android.app.Activity;
import android.content.Intent;


public class KayitOl extends Activity {
	
    EditText user,pass;
    Button kayit;
    String  personel,sifre,gonderId;
    
   /* private String url = "jdbc:mysql://atahan.net:1071/";
	private String dbName = "c1spor";
	private String driver = "com.mysql.jdbc.Driver";  
	private String userName = "c1spor"; 
	private String password = "14531071."; */
	
	private String url = "jdbc:mysql://85.159.67.247:3306/";
	private String dbName = "softwareG06";
	private String driver = "com.mysql.jdbc.Driver";  
	private String userName = "softwareG06"; 
	private String password = "softwareG06";  
	
	private Connection conn = null; 
	Statement stmt;
	ResultSet rsSpiner;
	ResultSetMetaData rsmdSpiner ;
	

	PreparedStatement ps;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.kayit_ol);
        
        kayit=(Button)findViewById(R.id.btnKayit);

        
 
 
        // Listening to register new account link
        kayit.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
                // Switching to Register screen
            	user=(EditText)findViewById(R.id.edtUsernameCreate);
    	        pass=(EditText)findViewById(R.id.edtPasswordCreate);
    	        
    	         personel = user.getText().toString();
    	         sifre = pass.getText().toString();
    	         if(personel.matches("")||sifre.matches("")){
            		 
    	        	 Toast.makeText(getApplicationContext(), "Kullanýcý adý yada þifre eksik girildi", 1).show();

            	 }
    	         else{
            		 try {
                 		
                	     
     					Class.forName(driver).newInstance();
     			        conn = DriverManager.getConnection(url+dbName,userName,password);
     			        stmt = conn.createStatement();
     			       String add= "INSERT INTO users (uname, password) VALUES (?,?)"; 
     			        ps=(PreparedStatement) conn.prepareStatement(add);
     			        ps.setString(1,personel);
     			        ps.setString(2,sifre);
     			        ps.execute();
     			       
     			       
            		 }
            		 catch(Exception e){
     			        	
            		 }
     	 
    	        	 Toast.makeText(getApplicationContext(), "Kaydýnýz baþarýlý bir þekilde oluþturuldu.", 1).show();

            		 Intent i=new Intent(KayitOl.this,GirisYap.class);
            		 startActivity(i);
    	         }
            
            		       
            		    
           }
        });
        
    }
}