package com.softwareG06.sanaldersanem;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class VideoOynat extends Activity implements OnInitializedListener,OnRatingBarChangeListener {
	
	public static final String API_KEY = "AIzaSyCe6tORd9Ch4lx-9Ku5SQ476uS9OtZYsWA";
	public static final String VIDEO_ID = "o7VVHhK9zf0";
	public static final String PlayList_ID = "PLP7qPet500dfglA7FFTxBmB_snxCaMHDJ";
	
	private Connection conn = null;  
	private String url = "jdbc:mysql://85.159.67.247:3306/";
	private String dbName = "softwareG06";
	private String driver = "com.mysql.jdbc.Driver";  
	private String userName = "softwareG06"; 
	private String password = "softwareG06";  
	TextView TV;
	ListView lv;
	
	private YouTubePlayer youTubePlayer;
	private YouTubePlayerFragment youTubePlayerFragment;
	private Button btnViewFullScreen;
	static String videoID,index,receivedListID,user;
	private static final int RQS_ErrorDialog = 1;
	
	Statement stmt;
	ResultSet rsSpiner;
	ResultSetMetaData rsmdSpiner ;
	PreparedStatement ps;
		
	static String log = "",sorgu,yorum,ratingg,sorguYorum;

	
	EditText yorumAl;
	RatingBar ratingBar;
	Button yorumYap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_oynat);
        
        ratingBar=(RatingBar) findViewById(R.id.ratingBar1);
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
    		public void onRatingChanged(RatingBar ratingBar, float rating,
    			boolean fromUser) {
     
    			ratingg=String.valueOf(rating);
     
    		}
    	});

       
        videoID=getIntent().getStringExtra(YGSDersler.ID_Extra);
		index=getIntent().getStringExtra("index");
		user    = getIntent().getStringExtra("uname");
		receivedListID=getIntent().getStringExtra("received"); 

		try {
			
			
			Class.forName(driver).newInstance();
	        conn = DriverManager.getConnection(url+dbName,userName,password);
	        stmt = conn.createStatement();
	        sorgu= "INSERT INTO video (video_id) VALUES (?)"; 
	        ps=(PreparedStatement) conn.prepareStatement(sorgu);
	        ps.setString(1,videoID);
	        ps.execute();
	       
	       
	        }
	        catch(Exception e){
	        	
	        }
        
	    yorumYap=(Button)findViewById(R.id.btnYorum);
	            
         youTubePlayerFragment = (YouTubePlayerFragment)getFragmentManager()
        	    .findFragmentById(R.id.youtubeplayerfragment);
        youTubePlayerFragment.initialize(API_KEY, this);
        sorguYorum="select uname,yorum,time,rating  from yorum inner join video on yorum.video_id=video.video_id inner join users on yorum.uid=users.uid  where yorum.video_id='"+videoID+"' ORDER BY time DESC";
        cagir(sorguYorum);
        yorumYap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar c = Calendar.getInstance();
		        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String formattedDate = df.format(c.getTime());
				try {
					
					
					
					yorumAl=(EditText)findViewById(R.id.edtYorumAl);
					yorum=yorumAl.getText().toString();
					Class.forName(driver).newInstance();
			        conn = DriverManager.getConnection(url+dbName,userName,password);
			        stmt = conn.createStatement();
			        String a=formattedDate;
			        sorgu= "INSERT INTO yorum (yorum,video_id,rating,time,uid) VALUES (?,?,?,?,?)"; 
			        ps=(PreparedStatement) conn.prepareStatement(sorgu);
			        ps.setString(1,yorum);
			        ps.setString(2,videoID);
			        ps.setString(3,ratingg);
			        ps.setString(4,a);
			        ps.setString(5,user);
			        ps.execute();
			       
			       
			        }
			        catch(Exception e){
			        	
			        }
				cagir(sorguYorum);
			}
			
		});
    }
    
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating,
      boolean fromTouch) {
     final int numStars = ratingBar.getNumStars();
    }

	@Override
	public void onInitializationFailure(Provider provider,
			YouTubeInitializationResult result) {
		
		if (result.isUserRecoverableError()) {
			result.getErrorDialog(this, RQS_ErrorDialog).show();	
		} else {
			Toast.makeText(this, 
					"YouTubePlayer.onInitializationFailure(): " + result.toString(), 
					Toast.LENGTH_LONG).show();	
		}
	}

	@Override
	public void onInitializationSuccess(Provider provider, YouTubePlayer player,
			boolean wasRestored) {
		  
		
		youTubePlayer = player;
			if (!wasRestored) {
			player.cueVideo(videoID);
		}

	}
	
	 public void cagir(String sorgulama){
			try {
				lv= (ListView)findViewById(R.id.lvYorum);
		   String[] from = new String[] {"textKurs", "textDers","textPlay","textRate"};
			    int[] to = new int[] { R.id.tvKul, R.id.tvYorum, R.id.tvDate, R.id.tvRate };
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
	           map.put("textRate","" + rsSpiner.getString(4));

	            fillMaps.add(map);
	            }
	            // fill in the grid_item layout
		        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.yorum_list_row, from, to);
		        lv.setAdapter(adapter);
		     // React to user clicks on item
		        
		        conn.close();

		    } catch (Exception e) {
		    	
		        e.printStackTrace();
		    }
					
		}	
	 
	
	
	
}

