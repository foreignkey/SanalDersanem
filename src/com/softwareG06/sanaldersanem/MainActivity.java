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

public class MainActivity extends Activity {
	
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
		setContentView(R.layout.activity_semi_circular_radial_menu);
		
		login=(Button)findViewById(R.id.btnLogin);
		register=(Button)findViewById(R.id.btnUyeOl);
		
		kulAdi=(EditText)findViewById(R.id.edtKulAdi);
		sifre=(EditText)findViewById(R.id.edtSifre);
		
		register.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent i=new Intent(MainActivity.this,Register.class);
				startActivity(i);
			}});
		
		 login.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					try {
						
						kul=kulAdi.getText().toString();
						sif=sifre.getText().toString();
						
				        Class.forName(driver).newInstance();
				        conn = DriverManager.getConnection(url+dbName,userName,password);
				        System.out.println("Connected to the database");
				        String result = "Database connection success\n";
				        Statement stmt = conn.createStatement();
				        ResultSet rs = stmt.executeQuery("select username,password from users ");
			            ResultSetMetaData rsmd = rs.getMetaData();

			            while(rs.next()) {
			            	if (kul == rs.getString("usersname")) {  
			                    if (sif == rs.getString("password")) { 
			                    	Intent i = new Intent(MainActivity.this, YGSMain.class);
					            	startActivity(i);
			                        System.out.println("Logged in!");  
			                    } else {  
			                        System.out.println("Password did not match username!");  
			                    }  
			                } else {  
			                       System.out.println("Username did not match the database"); 
			                }  
			            	
			            	
			            	//result += rs.getString(1) + "  ";
			            	//result += rs.getString(2) + "\n";
			            }
			           // TV.setText(result);
				        //String sql = "SELECT * FROM zz_sehir";
				        //stmt.execute(sql);
				        
				     // stmt.executeUpdate(sql); 

				        conn.close();
				        Toast.makeText(getApplicationContext(), "Sorgu yapýldý", Toast.LENGTH_SHORT).show();

				        System.out.println("Disconnected from database");
				    } catch (Exception e) {
				    	
				        e.printStackTrace();
				       // TV.setText(e.toString());
				    }
				}});
		
		mYGS = new SemiCircularRadialMenuItem("ygs", getResources().getDrawable(R.drawable.ygs), "YGS");
		mLYS = new SemiCircularRadialMenuItem("lys", getResources().getDrawable(R.drawable.lgs), "LYS");
		mKPSS = new SemiCircularRadialMenuItem("kpss", getResources().getDrawable(R.drawable.kpss), "KPSS");

				
		mMenu = (SemiCircularRadialMenu) findViewById(R.id.radial_menu);
		
		mMenu.addMenuItem(mLYS.getMenuID(), mLYS);
		mMenu.addMenuItem(mKPSS.getMenuID(), mKPSS);
		mMenu.addMenuItem(mYGS.getMenuID(), mYGS);
				
		mKPSS.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {
				Toast.makeText(MainActivity.this, mKPSS.getText(), Toast.LENGTH_LONG).show();
			}
		});
		
		
		mLYS.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {
				Toast.makeText(MainActivity.this, mLYS.getText(), Toast.LENGTH_LONG).show();
			}
		});
		
		
		
		mYGS.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {
				Intent i=new Intent(MainActivity.this,YGSMain.class);
				startActivity(i);				}
		});
		
		
	}
	
	
}
