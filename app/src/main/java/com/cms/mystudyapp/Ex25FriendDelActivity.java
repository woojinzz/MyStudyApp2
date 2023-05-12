package com.cms.mystudyapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ex25FriendDelActivity extends AppCompatActivity {
    TextView tvHp,tvSex, tvAddr, tvAge;
    EditText etName;

    Button btnDel, btnFind;
    //전달할 변수들
    String sendId, sendPw, sendName, sendHp, selectSex="남자";
    static SQLiteDatabase db;
    static MySQLiteOpenHelperFriends helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex25_del);
        //데이베이스 생성.
        helper = new MySQLiteOpenHelperFriends(
                Ex25FriendDelActivity.this, // 현재 화면의 context
                "friends2.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호

        etName = (EditText) findViewById(R.id.etName);

        tvAddr = (TextView) findViewById(R.id.tvAddr);
        tvAge = (TextView) findViewById(R.id.tvAge);
        tvHp = (TextView) findViewById(R.id.tvHp);
        tvSex = (TextView) findViewById(R.id.tvSex);

        btnFind = (Button) findViewById(R.id.btnFind);
        btnDel = (Button) findViewById(R.id.btnDel);

        btnFind.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                String findName = etName.getText().toString();

                if(findName.equals(""))
                {
                    Toast.makeText(Ex25FriendDelActivity.this,"이름을 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }

                for(int i=0; i<Ex25FriendsListActivity.adapter.getCount();i++)
                {
                    //Log.d("아이템값체크","아아템:"+Ex25FriendsListActivity.adapter.items.get(i).getName());
                    if(findName.equals(Ex25FriendsListActivity.adapter.items.get(i).getName()))
                    {
                        //값자리 초기화
                        etName.setText(Ex25FriendsListActivity.adapter.items.get(i).getName());
                        tvHp.setText(Ex25FriendsListActivity.adapter.items.get(i).getHp());
                        tvAddr.setText(Ex25FriendsListActivity.adapter.items.get(i).getAddr());
                        tvAge.setText(Ex25FriendsListActivity.adapter.items.get(i).getAge()+"");
                        tvSex.setText(Ex25FriendsListActivity.adapter.items.get(i).getSex());
                    }
                }


            }
        });
        btnDel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

                String findName = etName.getText().toString();
                if(findName.equals(""))
                {
                    Toast.makeText(Ex25FriendDelActivity.this,"이름을 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }
                for(int i=0; i<Ex25FriendsListActivity.adapter.getCount();i++)
                {
                    //Log.d("아이템값체크","아아템:"+Ex25FriendsListActivity.adapter.items.get(i).getName());
                    if(findName.equals(Ex25FriendsListActivity.adapter.items.get(i).getName()))
                    {
                        Ex25FriendsListActivity.adapter.items.remove(i);
                        break;
                    }
                }
                //메인에 있는 리스트뷰에 변화가 생겨서 갱신.
                Ex25FriendsListActivity.adapter.notifyDataSetChanged();

                //값자리 초기화
                etName.setText("");
                tvHp.setText("");
                tvAddr.setText("");
                tvSex.setText("");
                tvAge.setText("");

                Toast.makeText(Ex25FriendDelActivity.this,"삭제완료!",Toast.LENGTH_SHORT).show();
                delete(findName);
            }
        });

    }

    public void delete(String name) {
        db = helper.getWritableDatabase();
        db.delete("friends", "name='"+name+"'", null);
        Log.d("db", name + "가 정상적으로 삭제 되었습니다.");
        Toast.makeText(getApplicationContext(), name+"의 정보가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
        db.close();
    }

}