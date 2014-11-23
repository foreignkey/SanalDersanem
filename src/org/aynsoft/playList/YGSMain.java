package org.aynsoft.playList;


import br.com.dina.ui.widget.UITableView;
import br.com.dina.ui.widget.UITableView.ClickListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class YGSMain extends Activity {
    
	UITableView tableView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
        tableView = (UITableView) findViewById(R.id.tableView);        
        createList();        
        Log.d("MainActivity", "total items: " + tableView.getCount());        
        tableView.commit();
    }
    
    private void createList() {
    	CustomClickListener listener = new CustomClickListener();
    	tableView.setClickListener(listener);
    	tableView.addBasicItem("MATEMATÝK", "YGS MAT1");
    	tableView.addBasicItem("GEOMETRÝ", "YGS GEO");
    	tableView.addBasicItem("TÜRKÇE", "YGS TÜRKÇE");
    	tableView.addBasicItem("FÝZÝK", "YGS FÝZÝK1");
    	tableView.addBasicItem("KÝMYA", "YGS KÝMYA1");
    	tableView.addBasicItem("BÝYOLOJÝ", "YGS BÝYOLOJÝ");
    	tableView.addBasicItem("TARÝH", "YGS TARÝH");
    	tableView.addBasicItem("COÐRAFYA", "YGS COÐRAFYA");
    	tableView.addBasicItem("FELSEFE", "YGS FELSEFE");
    	
    }
    
    private class CustomClickListener implements ClickListener {

		@Override
		public void onClick(int index) {
			Log.d("MainActivity", "item clicked: " + index);
			Intent i = new Intent(YGSMain.this, YGSDersler.class);

			if(index == 0) {
				i.putExtra("title", "Matematik");
                i.putExtra("send", "PLoh2nz3f2DacT7u3wv-QjZ6dMglUATwpm");
				startActivity(i);
			}
			else if(index == 1) {
				i.putExtra("title", "Geometri");
                i.putExtra("send", "PLoh2nz3f2DacT7u3wv-QjZ6dMglUATwpm");
				startActivity(i);				
			}
			else if(index == 2) {
				i.putExtra("title", "Türkçe");
				 i.putExtra("send", "PLoh2nz3f2DacT7u3wv-QjZ6dMglUATwpm");
				startActivity(i);					
			}
			else if(index == 3) {
				i.putExtra("title", "Fizik");
				 i.putExtra("send", "PLoh2nz3f2Dae0_3YzfeCYSGI0z3_tKmT_");
					startActivity(i);					
			}
			else if(index == 4) {
				i.putExtra("title", "Kimya");
				 i.putExtra("send", "PLoh2nz3f2DacT7u3wv-QjZ6dMglUATwpm");
					startActivity(i);					
			}
			else if(index == 5) {
				i.putExtra("title", "Biyoloji");
				 i.putExtra("send", "PLoh2nz3f2DadQnKY7qPuEtTjXV4BXsuOO");
					startActivity(i);					
			}
			else if(index == 6) {
				i.putExtra("title", "Tarih");
				 i.putExtra("send", "PLoh2nz3f2DacT7u3wv-QjZ6dMglUATwpm");
					startActivity(i);				
			}
			else if(index == 7) {
				i.putExtra("title", "Coðrafya");
				 i.putExtra("send", "PLoh2nz3f2DaeLGQCuFbHG9Sv169cI_6FM");
					startActivity(i);	
			}
			else if(index == 8) {
				i.putExtra("title", "Felsefe");
				 i.putExtra("send", "PLoh2nz3f2DafE0vZENBoFzTwrZH11xdb6");
					startActivity(i);	
			}
			
		}
    	
    }
    
}