package com.softwareG06.sanaldersanem;

import java.sql.*;


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

public class VideoOynat extends Activity implements OnInitializedListener {
	
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
	private Button btnViewFullScreen;
	String videoID,index,receivedListID;
	private static final int RQS_ErrorDialog = 1;
		
	String log = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_oynat);
        
	    TV=(TextView)findViewById(R.id.textView1);

        
         youTubePlayerFragment = (YouTubePlayerFragment)getFragmentManager()
        	    .findFragmentById(R.id.youtubeplayerfragment);
        youTubePlayerFragment.initialize(API_KEY, this);       
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
		
		youTubePlayer = player;
			if (!wasRestored) {
			player.cueVideo(videoID);
		}

	}
	
}
