package com.cms.mystudyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Ex1JoinResultActivity extends AppCompatActivity {

    TextView tvId, tvPw, tvName, tvHp, tvSex, tvAddr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1_join_result);

        tvId = (TextView) findViewById(R.id.tvId);
        tvPw = (TextView) findViewById(R.id.tvPw);
        tvName = (TextView) findViewById(R.id.tvName);
        tvHp = (TextView) findViewById(R.id.tvHp);
        tvSex = (TextView) findViewById(R.id.tvSex);
        tvAddr = (TextView) findViewById(R.id.tvAddr);

        //전달받은 데이타 받기
        Intent getData = getIntent();
        tvId.setText(getData.getStringExtra("id"));
        tvPw.setText(getData.getStringExtra("pw"));
        tvName.setText(getData.getStringExtra("name"));
        tvHp.setText(getData.getStringExtra("hp"));
        tvSex.setText(getData.getStringExtra("sex"));
        tvAddr.setText(getData.getStringExtra("addr"));

    }
}