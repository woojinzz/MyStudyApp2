package com.cms.mystudyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Ex5DataActivity extends AppCompatActivity {

    public static String id, hp;
    EditText etIdStatic, etHpStatic, etId, etHp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex5_data);
        //id연동
        etIdStatic = (EditText) findViewById(R.id.etIdStatic);
        etHpStatic = (EditText) findViewById(R.id.etHpStatic);
        etId = (EditText) findViewById(R.id.etId);
        etHp = (EditText) findViewById(R.id.etHp);

        findViewById(R.id.btnStatic).setOnClickListener(mClickListener);
        findViewById(R.id.btnTrans).setOnClickListener(mClickListener);
    }

    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnStatic:
                    id = etIdStatic.getText().toString();
                    hp = etHpStatic.getText().toString();

                    etIdStatic.setText("");
                    etHpStatic.setText("");
                    Toast.makeText(Ex5DataActivity.this,"저장되었습니다.",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnTrans:
                    String getId = etId.getText().toString();
                    String getHp = etHp.getText().toString();

                    Intent intent1 = new Intent(Ex5DataActivity.this, Ex5DataReceiveActivity.class);
                    intent1.putExtra("id", getId);
                    intent1.putExtra("hp", getHp);
                    startActivity(intent1);
                    break;
            }
        }
    };
}