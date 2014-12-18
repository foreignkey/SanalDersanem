package com.softwareG06.sanaldersanem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


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

public class Giris extends Activity {
	
	private SemiCircularRadialMenu mMenu;
	private SemiCircularRadialMenuItem mYGS, mLYS,  mKPSS, mUyeOl, mKulGelen ;
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
		setContentView(R.layout.giris);
		
		mUyeOl = new SemiCircularRadialMenuItem("uye_ol", getResources().getDrawable(R.drawable.ic_action_search), "Üye Ol");
		mLYS = new SemiCircularRadialMenuItem("lys", getResources().getDrawable(R.drawable.lgs), "LYS");
		mKPSS = new SemiCircularRadialMenuItem("kpss", getResources().getDrawable(R.drawable.kpss), "KPSS");
		mYGS = new SemiCircularRadialMenuItem("ygs", getResources().getDrawable(R.drawable.ygs), "YGS");
		mKulGelen = new SemiCircularRadialMenuItem("kullanicidan_gelen", getResources().getDrawable(R.drawable.ygs), "Kullanýcýdan Gelen");

				
		mMenu = (SemiCircularRadialMenu) findViewById(R.id.radial_menu);
		
		
		mMenu.addMenuItem(mLYS.getMenuID(), mLYS);
		mMenu.addMenuItem(mYGS.getMenuID(), mYGS);
		mMenu.addMenuItem(mUyeOl.getMenuID(), mUyeOl);		
		mMenu.addMenuItem(mKPSS.getMenuID(), mKPSS);
		mMenu.addMenuItem(mKulGelen.getMenuID(), mKulGelen);
		
		
				
		mKPSS.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {
				Toast.makeText(Giris.this, mKPSS.getText(), Toast.LENGTH_LONG).show();
			}
		});
		
		
		mLYS.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {
				Toast.makeText(Giris.this, mLYS.getText(), Toast.LENGTH_LONG).show();
			}
		});
		
		
		
		mYGS.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {
				Intent i=new Intent(Giris.this,YGSMain.class);
				startActivity(i);				}
		});
		
		
		mUyeOl.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {
				Intent i=new Intent(Giris.this,GirisYap.class);
				startActivity(i);				}
		});
		mKulGelen.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {
				Intent i=new Intent(Giris.this,OynatmaListesiAl.class);
				startActivity(i);				}
		});
		
		
		
	}
	
	
}
