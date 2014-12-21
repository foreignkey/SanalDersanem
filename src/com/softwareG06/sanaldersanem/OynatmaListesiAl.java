package com.softwareG06.sanaldersanem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class OynatmaListesiAl extends Activity implements OnItemClickListener{

	String kurs,ders,playlist,add;
	TextView tv,tvKurs,tvDers,tvPlay;
	ListView lv;
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
		
		String sorgu="select kurs,ders,playlist from playlist_al group by kurs,ders";
	 
	@Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oynatma_listesi_al);
		/*veriAl = getIntent().getExtras();
		kurs    = veriAl.getString("kurs");
		ders=veriAl.getString("ders");
		playlist=veriAl.getString("playlist");
		
		tv=(TextView)findViewById(R.id.textView1);
		
		tv.setText("ders : "+ders+" kurs : "+kurs+" playlist : "+playlist);*/
		
		
		
		cagir(sorgu);
		
		
	}
	
	 public void cagir(String sorgulama){
			try {
				lv= (ListView)findViewById(R.id.lvKulGelen);
  		   String[] from = new String[] {"textKurs", "textDers","textPlay"};
			    int[] to = new int[] { R.id.tvKurs, R.id.tvDers,R.id.tvPlay };
		        // prepare the list of all records
		        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
		     
  		  
	               Class.forName(driver).newInstance();
		        conn = DriverManager.getConnection(url+dbName,userName,password);
		        System.out.println("Connected to the database");
		         stmt = conn.createStatement();
		         rsSpiner = stmt.executeQuery(sorgulama);
	             rsmdSpiner = rsSpiner.getMetaData();
	             
	            while(rsSpiner.next()) {

	            	HashMap<String, String> map = new HashMap<String, String>();
 	        	map.put("textKurs", "" + rsSpiner.getString(1));
 	            map.put("textDers","" + rsSpiner.getString(2));
 	           map.put("textPlay","" + rsSpiner.getString(3));

 	            fillMaps.add(map);
	            }
	            // fill in the grid_item layout
		        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.lv_kul_gelen_row, from, to);
		        lv.setAdapter(adapter);
		        lv.setOnItemClickListener(new OnItemClickListenerListViewItem());
		     // React to user clicks on item
		        
		        conn.close();

		    } catch (Exception e) {
		    	
		        e.printStackTrace();
		    }
					
		}	
	 
	 public class OnItemClickListenerListViewItem implements OnItemClickListener {

		    @Override
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		        Context context = view.getContext();

		        TextView textViewItem = ((TextView) view.findViewById(R.id.tvPlay));
		        TextView textViewItem2 = ((TextView) view.findViewById(R.id.tvDers));

		        // get the clicked item name
		        String listItemText = textViewItem.getText().toString();
		        String listItemText2 = textViewItem2.getText().toString();

		        Intent i = new Intent(OynatmaListesiAl.this, YGSDersler.class);

		        i.putExtra("title", listItemText2);
                i.putExtra("send", listItemText);
				startActivity(i);

		        // just toast it


		    }

		}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
