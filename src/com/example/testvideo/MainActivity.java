package com.example.testvideo;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.VideoView;

public class MainActivity extends Activity {

	public static final String TAG = "MainActivity";
	
	private SurfaceView mSurfaceView;
	private SurfaceHolder mSurfaceHolder;
	private MediaPlayer mPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	private void init(){
		String sdPath = getSDPath();
		if(sdPath != null){
			File file = new File(sdPath+"/video/movie_x264.mp4");
			Log.i(TAG, "exists="+file.exists());
			
			mPlayer = new MediaPlayer();
			try {
				mPlayer.setDataSource(file.getPath());
				mPlayer.setOnPreparedListener(prepareListener);
				
				
				mSurfaceView = (SurfaceView) findViewById(R.id.surface);
				mSurfaceHolder = mSurfaceView.getHolder();
				mSurfaceHolder.addCallback(callback);
//				mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	private SurfaceHolder.Callback callback = new Callback() {
		
		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			Log.i(TAG, "surfaceDestroyed");
			mPlayer.stop();
		}
		
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			Log.i(TAG, "surfaceCreated");
			mPlayer.setDisplay(mSurfaceHolder);
			mPlayer.prepareAsync();
		}
		
		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			Log.i(TAG, "surfaceChanged");
		}
	};
	
	private OnPreparedListener prepareListener = new OnPreparedListener() {
		
		@Override
		public void onPrepared(MediaPlayer mp) {
			Log.i(TAG, "onPrepared");
	        //首先取得video的宽和高 
	        int width = mp.getVideoWidth(); 
	        int height = mp.getVideoHeight(); 
	        int time = mp.getDuration();
	        Log.i(TAG, "w="+width+",h="+height+",time="+time);   
		}
	};
	

	private String getSDPath(){
		String path = null;
		if(Environment.getExternalStorageState().
				equals(android.os.Environment.MEDIA_MOUNTED)){
			path = Environment.getExternalStorageDirectory().getPath();
		}
		return path;
	}
	
	public void onStart(View view){
		Log.i(TAG, "start");
		mPlayer.start();
	}

	public void onStop(View view){
		Log.i(TAG, "stop");
		mPlayer.pause();
	}
	
	public void onTime(View view){
		Log.i(TAG, "time");
		Log.i(TAG, "time="+mPlayer.getCurrentPosition()+",isPlay="+mPlayer.isPlaying());
	}
	
	public void onSkip(View view){
		Log.i(TAG, "skip");
		mPlayer.seekTo(600000);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
