package com.cms.mystudyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Ex4GifActivity extends AppCompatActivity {
    ImageView ivMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex4_gif);
        ivMain = (ImageView)findViewById(R.id.ivMain);
        findViewById(R.id.btnGif1).setOnClickListener(mClickListener);
        findViewById(R.id.btnGif2).setOnClickListener(mClickListener);
        findViewById(R.id.btnGif3).setOnClickListener(mClickListener);
    }

    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnGif1:
                   Glide.with(Ex4GifActivity.this).load(R.drawable.img_gif1).into(ivMain);
                    break;
                case R.id.btnGif2:
                    Glide.with(Ex4GifActivity.this).load(R.drawable.img_gif2).into(ivMain);
                    break;
                case R.id.btnGif3:
                    Glide.with(Ex4GifActivity.this).load(R.drawable.img_gif3).into(ivMain);
                    break;

            }
        }
    };
}