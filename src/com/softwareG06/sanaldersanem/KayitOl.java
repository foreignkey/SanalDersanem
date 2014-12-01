package com.softwareG06.sanaldersanem;




import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


import android.app.Activity;


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
            	
            	
            	 try {
            		 user=(EditText)findViewById(R.id.edtUsernameCreate);
            	        pass=(EditText)findViewById(R.id.edtPasswordCreate);
            	        
            	         personel = user.getText().toString();
            	         sifre = pass.getText().toString();
            	        		    		        		              		  
            	         Class.forName(driver).newInstance();
            		        conn = DriverManager.getConnection(url+dbName,userName,password);
            		        System.out.println("Connected to the database");
            		        Statement stmt = conn.createStatement();
            		        String sql = "INSERT INTO 'users'('uid','uname', 'password') VALUES (12,"+personel+",'"+sifre+"')";
            		        stmt.execute(sql);
            		        
            		      //stmt.executeUpdate(sql); 

            		        conn.close();
            		        Toast.makeText(getApplicationContext(), "Veri eklendi", Toast.LENGTH_SHORT).show();
            		        System.out.println("Disconnected from database");
            		    } catch (Exception e) {
            		    	
            		        e.printStackTrace();
            		    }
            				           
;
            }
        });
    }
}