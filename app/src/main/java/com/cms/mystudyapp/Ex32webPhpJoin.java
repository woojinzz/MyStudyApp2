package com.cms.mystudyapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;


public class Ex32webPhpJoin extends AppCompatActivity {

   EditText etId, etPw, etName, etHp;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex32_web_join_layout);
        etId = (EditText)findViewById(R.id.etId);
        etPw = (EditText)findViewById(R.id.etPw);
        etName = (EditText)findViewById(R.id.etName);
        etHp = (EditText)findViewById(R.id.etHp);

        findViewById(R.id.btnJoin).setOnClickListener(mClickListener);
    }
    View.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v){


            switch (v.getId()) {
                case R.id.btnJoin :
                    String id = etId.getText().toString();
                    String pw = etPw.getText().toString();
                    String name = etName.getText().toString();
                    String hp = etHp.getText().toString();

                    if(id.equals(""))
                    {
                        Toast.makeText(Ex32webPhpJoin.this,"아이디를 입력하시오.",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(pw.equals(""))
                    {
                        Toast.makeText(Ex32webPhpJoin.this,"비번을 입력하시오..",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(name.equals(""))
                    {
                        Toast.makeText(Ex32webPhpJoin.this,"이름을 입력하시오.",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(hp.equals(""))
                    {
                        Toast.makeText(Ex32webPhpJoin.this,"연락처를 입력하시오.",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    join(id, pw, name, hp);
                    break;


            }
        }
    };
    void join(String id, String pw, String name, String hp)
    {

        Response.Listener<String> responseListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                try {


                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if(success)
                    {
                        Intent intentMain = new Intent(Ex32webPhpJoin.this, Ex32webPhpLogin.class);
                        startActivity(intentMain);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Ex32webPhpJoin.this,"회원가입실패! 관리자에게 문의하시오!",Toast.LENGTH_SHORT).show();
                        Log.d("회원가입실패","회원가입실패! 관리자에게 문의하시오!");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Ex32ServerRequestJoin getUserCharLevelStat = new Ex32ServerRequestJoin(id, pw, name, hp, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Ex32webPhpJoin.this);
        queue.add(getUserCharLevelStat);
    }


}