package com.cms.mystudyapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ex28FriendDelActivity extends AppCompatActivity {
    TextView tvHp,tvSex, tvAddr, tvAge;
    EditText etName;

    Button btnDel, btnFind;
    //전달할 변수들
    String sendId, sendPw, sendName, sendHp, selectSex="남자";
    static SQLiteDatabase db;
    static MySQLiteOpenHelperFriends2 helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex25_del);
        //데이베이스 생성.
        helper = new MySQLiteOpenHelperFriends2(
                Ex28FriendDelActivity.this, // 현재 화면의 context
                "members.db", // 파일명
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
                    Toast.makeText(Ex28FriendDelActivity.this,"이름을 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }

                for(int i=0; i<Ex28RecyclerViewFriends.adapter.getItemCount();i++)
                {
                    //Log.d("아이템값체크","아아템:"+Ex25FriendsListActivity.adapter.items.get(i).getName());
                    if(findName.equals(Ex28RecyclerViewFriends.adapter.itemData.get(i).getName()))
                    {
                        //값자리 초기화
                        etName.setText(Ex28RecyclerViewFriends.adapter.itemData.get(i).getName());
                        tvHp.setText(Ex28RecyclerViewFriends.adapter.itemData.get(i).getHp());
                        tvAddr.setText(Ex28RecyclerViewFriends.adapter.itemData.get(i).getAddr());
                        tvAge.setText(Ex28RecyclerViewFriends.adapter.itemData.get(i).getAge()+"");
                        tvSex.setText(Ex28RecyclerViewFriends.adapter.itemData.get(i).getSex());
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
                    Toast.makeText(Ex28FriendDelActivity.this,"이름을 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }
                for(int i=0; i<Ex28RecyclerViewFriends.adapter.getItemCount();i++)
                {
                    //Log.d("아이템값체크","아아템:"+Ex25FriendsListActivity.adapter.items.get(i).getName());
                    if(findName.equals(Ex28RecyclerViewFriends.adapter.itemData.get(i).getName()))
                    {
                        Ex28RecyclerViewFriends.adapter.itemData.remove(i);
                        break;
                    }
                }
                //메인에 있는 리스트뷰에 변화가 생겨서 갱신.
                Ex28RecyclerViewFriends.adapter.notifyDataSetChanged();

                //값자리 초기화
                etName.setText("");
                tvHp.setText("");
                tvAddr.setText("");
                tvSex.setText("");
                tvAge.setText("");

                Toast.makeText(Ex28FriendDelActivity.this,"삭제완료!",Toast.LENGTH_SHORT).show();
                delete(findName);
            }
        });

    }

    public void delete(String name) {
        db = helper.getWritableDatabase();
        db.delete("members", "name='"+name+"'", null);
        Log.d("db", name + "가 정상적으로 삭제 되었습니다.");
        Toast.makeText(getApplicationContext(), name+"의 정보가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
        db.close();
    }

}