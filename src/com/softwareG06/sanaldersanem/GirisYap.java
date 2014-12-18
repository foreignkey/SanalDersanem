package com.softwareG06.sanaldersanem;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class GirisYap extends Activity {
	
    EditText user,pass;
    Button login,kayitOl;
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
        setContentView(R.layout.giris_yap);
        
        login=(Button)findViewById(R.id.btnLogin);
        kayitOl=(Button)findViewById(R.id.btnRegister);

     // Listening to register new account link
        kayitOl.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
                // Switching to Register screen
            	Intent i=new Intent(GirisYap.this, KayitOl.class);
            	startActivity(i);
            }
        });
        
 
 
        // Listening to register new account link
        login.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
                // Switching to Register screen
            	
            	
            	 try {
            		 user=(EditText)findViewById(R.id.edtUsername);
            	        pass=(EditText)findViewById(R.id.edtPassword);
            	        
            	         personel = user.getText().toString();
            	         sifre = pass.getText().toString();
            	        		    		        		              		  
      	        Class.forName(driver).newInstance();
      	        conn = DriverManager.getConnection(url+dbName,userName,password);
      	        System.out.println("Connected to the database");
      	        String result = "Database connection success\n";
      	        stmt = conn.createStatement();
      	        rsSpiner = stmt.executeQuery("SELECT password,uid,uname FROM users where uname='"+personel+"'");
                  rsmdSpiner = rsSpiner.getMetaData();
                  int numberOfColumns = rsmdSpiner.getColumnCount();
                  if (rsSpiner != null) {
                      while (rsSpiner.next()) {
                  String dbpass = rsSpiner.getString(1);
                  String idGonder = rsSpiner.getString(2);
                  if(dbpass.equals(sifre)){
                	 Intent i = new Intent(getApplicationContext(), Giris.class);
                	 i.putExtra("uname",rsSpiner.getString(3) );
                      startActivity(i);
                	  Toast.makeText(getApplicationContext(),"Correct Username AND Password", Toast.LENGTH_SHORT).show();

                  }
                  else
                  {
                	  Toast.makeText(getApplicationContext(),"Sorry!! Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                  }
                  }
                  }
      	        conn.close();
      	        Toast.makeText(getApplicationContext(), "Sorgu yapýldý, ÞÝFRE :"+sifre, Toast.LENGTH_SHORT).show();

      	        System.out.println("Disconnected from database");
      	    } catch (Exception e) {
      	    	
      	        e.printStackTrace();
      	       // tv.setText(e.toString());
      	    }
            	
              // Intent i = new Intent(getApplicationContext(), Sporsalonu.class);
               //startActivity(i);
            }
        });
    }
}