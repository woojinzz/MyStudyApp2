package com.cms.mystudyapp;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Ex6SpinnerActivity extends AppCompatActivity {
    TextView textView;

    String[] items = {"안드로이드", "아이폰", "PC프로그램", "테블릿"};

    String temp = "";//스피너 선택값 저장변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex6_spinner);

        textView = (TextView) findViewById(R.id.tvSel);

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
                textView.setText(items[position]);
                temp = items[position];
                Log.d("스피너테스트", "선택값 저장된 변수 temp : " + temp);
                if(position == 0)
                {
                    Toast.makeText(Ex6SpinnerActivity.this,"첫번째항목~!",Toast.LENGTH_SHORT).show();
                }
                else if(position == 1)
                {
                    Toast.makeText(Ex6SpinnerActivity.this,"두번째항목~!",Toast.LENGTH_SHORT).show();
                }
                else if(position == 2)
                {
                    Toast.makeText(Ex6SpinnerActivity.this,"세번째항목~!",Toast.LENGTH_SHORT).show();
                }
                else if(position == 3)
                {
                    Toast.makeText(Ex6SpinnerActivity.this,"네번째항목~!",Toast.LENGTH_SHORT).show();
                }
                if(items[position].equals("아이폰"))
                {
                    Toast.makeText(Ex6SpinnerActivity.this,"아이폰선택!~!",Toast.LENGTH_SHORT).show();
                }
                else if(items[position].equals("테블릿"))
                {
                    Toast.makeText(Ex6SpinnerActivity.this,"테블릿 선택함!!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textView.setText("선택 : ");
            }
        });

    }
}