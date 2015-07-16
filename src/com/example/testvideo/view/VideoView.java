package com.example.testvideo.view;

import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class VideoView extends SurfaceView implements SurfaceHolder.Callback{

	public static final String TAG = "VideoView";
	
	private Context mContext;
	private SurfaceHolder mHolder;
	private MediaPlayer mPlayer;
	
	
	public VideoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init();
	}

	public VideoView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public VideoView(Context context) {
		this(context, null);
	}
	
	private void init(){
		mHolder = getHolder();
		mPlayer = new MediaPlayer();
	}
	
	public void setDataSource(String path){
		if(mPlayer != null && mPlayer.isPlaying()){
			
		}else{
			
		}
		
	}
	
	public void setDataSource(Uri uri){
		try {
			mPlayer.setDataSource(mContext, uri);
			
		
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
	

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.i(TAG, "surfaceCreated");
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.i(TAG, "surfaceChanged");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.i(TAG, "surfaceDestroyed");
	}

	
	
}
