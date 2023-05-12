package com.cms.mystudyapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Ex12SoccerGameEx extends AppCompatActivity {
        ImageView ivMain;
        TextView tvCount, tvGoal, tvFail, tvMent, tvTot;
        int gameCount =10;//총게임수
        int gameTot = 0;//총게임전적
        int goal=0; //골넣은거
        int fail=0; //골실패

        Animation ani1,ani2;
        Button btnAct1,btnAct2,btnAct3, btnRestart;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ex12_soccer);

            tvCount= (TextView) findViewById(R.id.tvCount);
            tvGoal= (TextView) findViewById(R.id.tvGoal);
            tvFail= (TextView) findViewById(R.id.tvFail);
            tvMent= (TextView) findViewById(R.id.tvMent);
            tvTot= (TextView) findViewById(R.id.tvTot);

            btnAct1 = (Button) findViewById(R.id.btnAct1);
            btnAct2 = (Button) findViewById(R.id.btnAct2);
            btnAct3 = (Button) findViewById(R.id.btnAct3);
            btnRestart = (Button) findViewById(R.id.btnRestart);
            ivMain = (ImageView) findViewById(R.id.ivMain);
            findViewById(R.id.btnAct1).setOnClickListener(mClick);
            findViewById(R.id.btnAct2).setOnClickListener(mClick);
            findViewById(R.id.btnAct3).setOnClickListener(mClick);
            findViewById(R.id.btnRestart).setOnClickListener(mClick);

            ani1 = AnimationUtils.loadAnimation(Ex12SoccerGameEx.this, R.anim.set_goal);
            ani2 = AnimationUtils.loadAnimation(Ex12SoccerGameEx.this, R.anim.scale2);

            //초기화면
            tvCount.setText(gameCount+"회");
            tvGoal.setText(goal+"회");
            tvFail.setText(fail+"회");
            tvTot.setText(gameTot+"회");
            btnRestart.setVisibility(View.INVISIBLE);
        }
        void gameStart(int gubun)
        {
            if(gameCount<=0)
            {
                Toast.makeText(Ex12SoccerGameEx.this,"게임다시시작하기를 누르세요!",Toast.LENGTH_SHORT).show();
                return;
            }


            if(gubun==1)
            {
                if(gameCount<=1)
                {
                    Toast.makeText(Ex12SoccerGameEx.this,"게임카운트가부족합니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                btnAct1.startAnimation(ani2);
                gameCount-=2;//2차감
                int rand = (int)(Math.random()*10);
                if(rand>=0 && rand<3)
                {
                    tvMent.setText("중거리슛 성공!");
                    goal++;
                    Glide.with(Ex12SoccerGameEx.this).load(R.drawable.goal1).into(ivMain);
                }
                else {
                    tvMent.setTextColor(Color.parseColor("#FF0000"));
                    tvMent.setText("안들어감!슛실패!쉣!");

                    fail++;
                    //Glide.with(Ex12SoccerGameEx.this).load(R.drawable.goal_fail).into(ivMain);
                    ivMain.setImageResource(R.drawable.ball2);
                    ivMain.startAnimation(ani1);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable(){
                        @Override
                        public void run() {

                            ivMain.setImageResource(R.drawable.goal1_fail);
                        }
                    },2000); //3초 후 인트로 실행

                }
            }else if(gubun==2)
            {
                if(gameCount<=0)
                {
                    Toast.makeText(Ex12SoccerGameEx.this,"게임카운트가 부족합니다.",Toast.LENGTH_SHORT).show();
                    return;
                }

                btnAct2.startAnimation(ani2);
                gameCount-=1;//1차감
                int rand = (int)(Math.random()*10);
                if(rand>=0 && rand<3)
                {
                    tvMent.setText("헤딩슛 성공!");
                    goal++;
                    Glide.with(Ex12SoccerGameEx.this).load(R.drawable.goal2).into(ivMain);
                }
                else {
                    tvMent.setTextColor(Color.parseColor("#FF0000"));
                    tvMent.setText("안들어감!슛실패!쉣!");
                    fail++;
                    ivMain.setImageResource(R.drawable.ball2);
                    ivMain.startAnimation(ani1);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable(){
                        @Override
                        public void run() {

                            ivMain.setImageResource(R.drawable.goal1_fail);
                        }
                    },2000); //3초 후 인트로 실행
                }
            }else if(gubun==3)
            {
                if(gameCount<=2)
                {
                    Toast.makeText(Ex12SoccerGameEx.this,"게임카운트가 부족합니다.",Toast.LENGTH_SHORT).show();
                    return;
                }

                btnAct3.startAnimation(ani2);
                gameCount-=3;//2차감
                int rand = (int)(Math.random()*10);
                if(rand>=0 && rand<9)
                {
                    tvMent.setText("패널티 성공!");
                    goal++;
                    Glide.with(Ex12SoccerGameEx.this).load(R.drawable.goal1).into(ivMain);
                }
                else {
                    tvMent.setTextColor(Color.parseColor("#FF0000"));
                    tvMent.setText("안들어감!슛실패!쉣!");
                    fail++;
                    ivMain.setImageResource(R.drawable.ball2);
                    ivMain.startAnimation(ani1);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable(){
                        @Override
                        public void run() {

                            ivMain.setImageResource(R.drawable.goal1_fail);
                        }
                    },2000); //3초 후 인트로 실행
                }
            }
            gameTot++;
            tvCount.setText(gameCount+"회");
            tvGoal.setText(goal+"회");
            tvFail.setText(fail+"회");
            tvTot.setText(gameTot+"회");
            tvMent.setTextColor(Color.parseColor("#000000"));
            //게임카운트가0이되면 게임다시하기버튼보이기
            if(gameCount==0)
            btnRestart.setVisibility(View.VISIBLE);
        }
        View.OnClickListener mClick = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                switch(v.getId())
                {
                    case R.id.btnAct1:
                        gameStart(1);
                        break;
                    case R.id.btnAct2:
                        gameStart(2);
                        break;
                    case R.id.btnAct3:
                        gameStart(3);
                        break;
                    case R.id.btnRestart:
                        gameCount=10;
                        goal=0;
                        fail=0;
                        gameTot=0;
                        tvMent.setText("대기중...");
                        ivMain.setImageResource(R.drawable.game_bg2);
                        break;

                }

            }
        };

    }