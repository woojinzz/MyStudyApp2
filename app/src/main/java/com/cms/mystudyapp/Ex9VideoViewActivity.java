package com.cms.mystudyapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;


public class Ex9VideoViewActivity extends AppCompatActivity {

    VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex9_video);
        vv = (VideoView)findViewById(R.id.videoView);

        findViewById(R.id.btnAct1).setOnClickListener(mClick);
        findViewById(R.id.btnAct2).setOnClickListener(mClick);
        findViewById(R.id.btnAct3).setOnClickListener(mClick);
        Uri videoUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.bonobono);
        vv.setMediaController(new MediaController(this));
        vv.setVideoURI(videoUri);
    }
    View.OnClickListener mClick = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.btnAct1:
                    vv.start();
                    break;
                case R.id.btnAct2:
                    vv.pause();
                    break;
                case R.id.btnAct3:
                    vv.stopPlayback();
                    break;

            }

        }
    };


}