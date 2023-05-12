package com.cms.mystudyapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ex1JoinActivity extends AppCompatActivity {
    Switch swSex;
    TextView tvSex;

    EditText etId, etPw, etName, etHp;
    Button btnJoin;
    String[] items = {"서울", "대전", "대구", "부산", "광주", "인천", "경기", "제주", "강원"};

    //전달할 변수들
    String sendId, sendPw, sendName, sendHp, sendSex="남자", sendAddr="서울";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1_join);

        etId = (EditText) findViewById(R.id.etId);
        etPw = (EditText) findViewById(R.id.etPw);
        etName = (EditText) findViewById(R.id.etName);
        etHp = (EditText) findViewById(R.id.etHp);

        swSex = (Switch) findViewById(R.id.swSex);
        tvSex = (TextView) findViewById(R.id.tvSex);
        btnJoin = (Button) findViewById(R.id.btnJoin);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 스피너에 어댑터 설정
        spinner.setAdapter(adapter);

        // 스피너에서 선택 했을 경우 이벤트 처리
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sendAddr = items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sendAddr ="서울";
            }
        });

        swSex = findViewById(R.id.swSex);
        swSex.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    tvSex.setText("여자");
                    sendSex ="여자";
                } else {
                    tvSex.setText("남자");
                    sendSex = "남자";
                }
            }
        });
        btnJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                sendId = etId.getText().toString();
                sendPw = etPw.getText().toString();
                sendName = etName.getText().toString();
                sendHp = etHp.getText().toString();


                Intent intent = new Intent(Ex1JoinActivity.this, Ex1JoinResultActivity.class);
                intent.putExtra("id", sendId);
                intent.putExtra("pw", sendPw);
                intent.putExtra("name", sendName);
                intent.putExtra("hp", sendHp);
                intent.putExtra("sex", sendSex);
                intent.putExtra("addr", sendAddr);
                startActivity(intent);
            }
        });
    }
}