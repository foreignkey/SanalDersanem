package com.softwareG06.sanaldersanem;

import java.sql.*;

import org.aynsoft.playList.R;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class YouTubePlayerActivity extends Activity implements OnInitializedListener {
	
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
	
	
	private YouTubePlayer youTubePlayer;
	private YouTubePlayerFragment youTubePlayerFragment;
	//private TextView textVideoLog;
	private Button btnViewFullScreen;
	String videoID,index,receivedListID;
	private static final int RQS_ErrorDialog = 1;
	
	/*private MyPlayerStateChangeListener myPlayerStateChangeListener;
	private MyPlaybackEventListener myPlaybackEventListener;*/
	
	String log = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_extend);
        
	    TV=(TextView)findViewById(R.id.textView1);

        
         youTubePlayerFragment = (YouTubePlayerFragment)getFragmentManager()
        	    .findFragmentById(R.id.youtubeplayerfragment);
        youTubePlayerFragment.initialize(API_KEY, this);
        
        insertTable();
        cagir();

      //  textVideoLog = (TextView)findViewById(R.id.videolog);
        
       /* myPlayerStateChangeListener = new MyPlayerStateChangeListener();
        myPlaybackEventListener = new MyPlaybackEventListener();*/
        /*btnViewFullScreen = (Button)findViewById(R.id.btnviewfullscreen);
        btnViewFullScreen.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				youTubePlayer.setFullscreen(true);
			}});*/
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
		
		videoID=getIntent().getStringExtra(YGSDersler.ID_Extra);
		index=getIntent().getStringExtra("index");
		receivedListID=getIntent().getStringExtra("received");
		//Toast.makeText(getApplicationContext(), videoID+" "+index+" "+receivedListID, Toast.LENGTH_SHORT).show();
      
		
		youTubePlayer = player;
		
	//	Toast.makeText(getApplicationContext(), "YouTubePlayer.onInitializationSuccess()", Toast.LENGTH_LONG).show();
		
		//youTubePlayer.setPlayerStateChangeListener(myPlayerStateChangeListener);
	//	youTubePlayer.setPlaybackEventListener(myPlaybackEventListener);
       // youTubePlayer.setFullscreen(true);

		if (!wasRestored) {
			player.cueVideo(videoID);
			//player.cuePlaylist(PlayList_ID);
		}

	}
	


	public void insertTable(){
		try {
			//videoID=getIntent().getStringExtra(YGSDersler.ID_Extra);

	        Class.forName(driver).newInstance();
	        conn = DriverManager.getConnection(url+dbName,userName,password);
	        System.out.println("Connected to the database");
	        String result = "Database connection success\n";
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("INSERT INTO video VALUES ('"+videoID+"')");
            ResultSetMetaData rsmd = rs.getMetaData();

            while(rs.next()) {
            	result += rs.getString(1) + "  ";
            	//result += rs.getString(2) + "\n";
            }
            TV.setText(result);
	        //String sql = "SELECT * FROM zz_sehir";
	        //stmt.execute(sql);
	        
	     // stmt.executeUpdate(sql); 

	        conn.close();
	        Toast.makeText(getApplicationContext(), "Sorgu yapýldý", Toast.LENGTH_SHORT).show();

	        System.out.println("Disconnected from database");
	    } catch (Exception e) {
	    	
	        e.printStackTrace();
	        TV.setText(e.toString());
	    }
		
	}
	
	public void cagir(){
		try {
			
	        Class.forName(driver).newInstance();
	        conn = DriverManager.getConnection(url+dbName,userName,password);
	        System.out.println("Connected to the database");
	        String result = "Database connection success\n";
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("Select * from video");
            ResultSetMetaData rsmd = rs.getMetaData();

            while(rs.next()) {
            	result += rs.getString(1) + "  ";
            	//result += rs.getString(2) + "\n";
            }
            TV.setText(result);
	        //String sql = "SELECT * FROM zz_sehir";
	        //stmt.execute(sql);
	        
	     // stmt.executeUpdate(sql); 

	        conn.close();
	        Toast.makeText(getApplicationContext(), "Sorgu yapýldý", Toast.LENGTH_SHORT).show();

	        System.out.println("Disconnected from database");
	    } catch (Exception e) {
	    	
	        e.printStackTrace();
	        TV.setText(e.toString());
	    }
		
	}
}
