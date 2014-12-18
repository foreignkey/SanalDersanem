package com.softwareG06.sanaldersanem;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NodeList;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.softwareG06.sanaldersanem.adapter.VideoListAdapter;
import com.softwareG06.sanaldersanem.java.Parser;
import com.softwareG06.sanaldersanem.java.VideoDetail;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


public class YGSDersler extends Activity implements  OnItemClickListener,YouTubePlayer.OnInitializedListener{

	private ListView videoList;
	private VideoListAdapter adapter;
	private List<VideoDetail > list;
	public static final String ID_Extra="ID";
	public static final String PROCESS_DIALOG_MSG="Loading...";
	
	 Bundle paketim  = new  Bundle();
	 String receive,getTitle,user;
     	
	private static String url;//Number of max counts you want.
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		
		
		paketim = getIntent().getExtras();
		receive    = paketim.getString("send");
		getTitle=paketim.getString("title");
		user    = paketim.getString("uname");
		setTitle(getTitle);
		
		url="http://gdata.youtube.com/feeds/api/playlists/" +
				receive+//Your playlist ID
				"?v=2&max-results=50";
		
		list=new ArrayList<VideoDetail>();
		adapter=new VideoListAdapter(YGSDersler.this, list);		
		setContentView(R.layout.video_listview);
		
		
		videoList=(ListView)this.findViewById(R.id.movie_list_view);
		videoList.setAdapter(adapter);
		videoList.setOnItemClickListener(this);		

		//StartLoading
		startLoading(url);
	}	
	
	
	public void startLoading(String url){
		new LoadMoviesAsync().execute(url);
	}
	
	class LoadMoviesAsync extends AsyncTask<String,VideoDetail,Void>{
		ProgressDialog dialog;
		@Override
		protected void onPreExecute() {
			dialog=new ProgressDialog(YGSDersler.this);
			dialog.setMessage(PROCESS_DIALOG_MSG);
			dialog.show();
			super.onPreExecute();
		}		
		@Override
		protected Void doInBackground(String... params) {
			String url=params[0];			
			Parser parser=new Parser();
			NodeList movieContentLst=parser.getResponceNodeList(url);
			//Log.i("HomeActivity",""+movieContentLst.getLength());
			if(movieContentLst!=null){
				for(int i=0;i<movieContentLst.getLength();i++){
					publishProgress(parser.getResult(movieContentLst, i));
				}
			}
			
			return null;
		}
		
		@Override
		protected void onProgressUpdate(VideoDetail... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			addItem(values);
			adapter.notifyDataSetChanged();
		}	
		public void addItem(VideoDetail... items){
			for(VideoDetail item: items){
				list.add(item);
			}	
		}
		@Override
		protected void onPostExecute(Void result) {
			if(dialog.isShowing()){
				dialog.dismiss();
			}		
			Toast.makeText(getBaseContext(), ""+list.size(),Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		int a=arg2+1;
		Intent i=new Intent(YGSDersler.this,VideoOynat.class);
		i.putExtra(ID_Extra,""+list.get(arg2).getVideo_id());
		i.putExtra("index",""+a);
		i.putExtra("uname",user );
		i.putExtra("received",""+receive);
		startActivity(i);			
	}


	@Override
	public void onInitializationFailure(Provider arg0,
			YouTubeInitializationResult arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onInitializationSuccess(Provider arg0, YouTubePlayer arg1,
			boolean arg2) {
		// TODO Auto-generated method stub
		
	}
	
}
