package org.aynsoft.playList;

import com.touchmenotapps.widget.radialmenu.semicircularmenu.SemiCircularRadialMenu;
import com.touchmenotapps.widget.radialmenu.semicircularmenu.SemiCircularRadialMenuItem;
import com.touchmenotapps.widget.radialmenu.semicircularmenu.SemiCircularRadialMenuItem.OnSemiCircularRadialMenuPressed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private SemiCircularRadialMenu mMenu;
	private SemiCircularRadialMenuItem mYGS, mLYS,  mKPSS;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_semi_circular_radial_menu);
		
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
