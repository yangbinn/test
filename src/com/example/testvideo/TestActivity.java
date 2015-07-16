package com.example.testvideo;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class TestActivity extends Activity{

	public static final String TAG = "TestActivity";
	
	private VideoView mView;
	private MediaController mController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);						//隐藏标题栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,	//隐藏状态栏
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_test);
		init();
	}
	
	private void init(){
		mView = (VideoView) findViewById(R.id.video);
		mController = new MediaController(this);
		String sdPath = getSDPath();
		if(sdPath != null){
			File file = new File(sdPath+"/video/movie_x264.mp4");
			Log.i(TAG, "exists="+file.exists()+",file="+file.getPath());
			mView.setVideoPath(file.getPath());
			mView.setMediaController(mController);
			mController.setMediaPlayer(mView);
			mView.requestFocus();
			mView.start();
		}
	}
	
	private String getSDPath(){
		String path = null;
		if(Environment.getExternalStorageState().
				equals(android.os.Environment.MEDIA_MOUNTED)){
			path = Environment.getExternalStorageDirectory().getPath();
		}
		return path;
	}
}
