package com.cms.mystudyapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Ex10MediaActivity extends AppCompatActivity {

    MediaPlayer player;
    TextView tvMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex10_media);
        player = MediaPlayer.create(Ex10MediaActivity.this, R.raw.bells);
        tvMsg = ( TextView)findViewById(R.id.tvMsg);
        findViewById(R.id.btnAct1).setOnClickListener(mClick);
        findViewById(R.id.btnAct2).setOnClickListener(mClick);
        findViewById(R.id.btnAct3).setOnClickListener(mClick);

    }
    View.OnClickListener mClick = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.btnAct1:
                    player.start();
                    tvMsg.setText("음악이 재생중입니다.");
                    break;
                case R.id.btnAct2:
                    player.pause();
                    tvMsg.setText("음악이 일시정지 중입니다.");
                    break;
                case R.id.btnAct3:
                    tvMsg.setText("음악이 중지되었습니다.");
                    player.stop();
                    break;

            }

        }
    };


}