package com.cms.mystudyapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    Animation ani1,ani2,ani3,ani4;
    ImageView ivLogo1,ivLogo2,ivLogo3;
    LinearLayout lyBg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ani1 = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.intro_translate);
        ani2 = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.intro_scale);
        ani3 = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.intro_logo);
        ani4 = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.intro_scale2);

        lyBg = (LinearLayout)findViewById(R.id.lyBg);

        ivLogo1 = (ImageView)findViewById(R.id.ivLogo1);
        ivLogo2 = (ImageView)findViewById(R.id.ivLogo2);
        ivLogo3 = (ImageView)findViewById(R.id.ivLogo3);
        //ivLogo4 = (ImageView)findViewById(R.id.ivLogo2);

        ivLogo1.startAnimation(ani1);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                ivLogo2.setVisibility(View.VISIBLE);
                ivLogo2.startAnimation(ani2);
            }
        },2000);
        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable(){
            @Override
            public void run() {
                ivLogo3.setVisibility(View.VISIBLE);
                ivLogo3.startAnimation(ani3);
            }
        },3000); //1초 후 인트로 실행
        Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable(){
            @Override
            public void run() {
                ivLogo3.setVisibility(View.INVISIBLE);
                ivLogo3.setImageResource(R.drawable.logo3);
                ivLogo3.startAnimation(ani4);
            }
        },6000); //1초 후 인트로 실행
        Handler handler4 = new Handler();
        handler4.postDelayed(new Runnable(){
            @Override
            public void run() {
                lyBg.setBackgroundResource(R.drawable.logo4);
            }
        },9000); //1초 후 인트로 실행
        Handler handler5 = new Handler();
        handler5.postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },10000); //1초 후 인트로 실행
    }
}