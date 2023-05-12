package com.cms.mystudyapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ex24SQLiteExLogin extends AppCompatActivity {

    EditText etId, etPw;

    //SQLITE 데이타베이스 관련변수
    SQLiteDatabase db;
    MySQLiteOpenHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex24_sqlite_login);

        helper = new MySQLiteOpenHelper(
                Ex24SQLiteExLogin.this, // 현재 화면의 context
                "member.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호


        etId = (EditText)findViewById(R.id.etId);
        etPw = (EditText)findViewById(R.id.etPw);

        findViewById(R.id.btnLogin).setOnClickListener(mClick);
        findViewById(R.id.btnJoin).setOnClickListener(mClick);


    }
    View.OnClickListener mClick = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.btnLogin:
                    String id = etId.getText().toString();//화면에 입력한 아이디 가져오기
                    String pw = etPw.getText().toString();//화면에 입력한 패스워드 가져오기
                    if(id.equals("")){
                        Toast.makeText(Ex24SQLiteExLogin.this, "아이디를 입력하세요.",  Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(pw.equals("")){
                        Toast.makeText(Ex24SQLiteExLogin.this, "비밀번호를 입력하세요.",  Toast.LENGTH_SHORT).show();
                        return;
                    }
                    boolean loginCheck = false;

                    //디비 테이블에 id, pw 보내서 로그인 처리
                    loginCheck = dbLoginCheck(id,pw);


                    if(loginCheck == true)
                    {
                        Intent login = new Intent(Ex24SQLiteExLogin.this, Ex24SQLiteMainActivity.class);
                        startActivity(login);
                    }
                    else {
                        Toast.makeText(Ex24SQLiteExLogin.this, "로그인에 실패하였습니다.",  Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btnJoin:
                    Intent join = new Intent(Ex24SQLiteExLogin.this, Ex24SQLiteExJoin.class);
                    startActivity(join);
                    break;
            }

        }
    };

    public boolean dbLoginCheck(String loginId, String loginPw) {

        db = helper.getReadableDatabase(); // db객체를 얻어온다. 읽기 전용
        Cursor c = db.rawQuery("SELECT * FROM member", null);

        while (c.moveToNext()) {

            int idx = c.getInt(0);
            String id = c.getString(1);
            String pw = c.getString(2);
            String name = c.getString(3);
            String hp = c.getString(4);
            String addr = c.getString(5);

//            Log.d("로그인", "idx: " + idx + ", id : " + id + ", name : " + name
//                    + ", hp : " + hp+ ", addr : " + addr);
            if(loginId.equals(id))
            {
                if(loginPw.equals(pw))
                {
                    //아이디 패스워드를 최종적으로 잘 맞을경우 다 닫고 true값 리턴
                    c.close();
                    db.close();
//                    Log.d("로그인-성공", "idx: " + idx + ", id : " + id + ", name : " + name
//                            + ", hp : " + hp+ ", addr : " + addr);
                    return true;
                }
            }


        }
        c.close();
        db.close();

        return false;
    }


}