package com.cms.mystudyapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Ex28RecyclerViewFriends extends AppCompatActivity implements Ex28RecyclerAdapter.MyRecyclerViewClickListener {
    static ArrayList<Ex28ItemData> dataList = new ArrayList<>();
    static Ex28RecyclerAdapter adapter = new Ex28RecyclerAdapter(dataList);
    static int i=0;
    static RecyclerView recyclerView;
    static SQLiteDatabase db;
    static MySQLiteOpenHelperFriends2 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex28_recyclerview2);

        //데이베이스 생성.
        helper = new MySQLiteOpenHelperFriends2(
                Ex28RecyclerViewFriends.this, // 현재 화면의 context
                "members.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호


        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        for (int i=0; i<20; i++) {
//            dataList.add(new Ex28ItemData("홍길동-"+i,"0101234567"+i,"대전 "+i+"번지","남자"+i+"호",20+i));
//        }
        //첫화면에 디비읽어서 리사이클러뷰에 뿌리기...
        selectFriendsList();

        //버튼 이벤트처리
        findViewById(R.id.btnAdd).setOnClickListener(mClick);
        findViewById(R.id.btnEdit).setOnClickListener(mClick);
        findViewById(R.id.btnDel).setOnClickListener(mClick);
        adapter.setOnClickListener(this);
    }
    View.OnClickListener mClick = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.btnAdd:
                    Intent intentAdd = new Intent(Ex28RecyclerViewFriends.this, Ex28FriendAddActivity.class);
                    startActivity(intentAdd);
                    break;
                case R.id.btnEdit:
                    Intent intentEdit = new Intent(Ex28RecyclerViewFriends.this, Ex28FriendEditActivity.class);
                    startActivity(intentEdit);
                    break;
                case R.id.btnDel:
                    Intent intentDel = new Intent(Ex28RecyclerViewFriends.this, Ex28FriendDelActivity.class);
                    startActivity(intentDel);
                    break;
            }
        }
    };
    @Override
    public void onNameClicked(int position) {
        Toast.makeText(getApplicationContext(), "이름 : "+position, Toast.LENGTH_SHORT).show();
    }

    public void onHpClicked(int position) {
        Toast.makeText(getApplicationContext(), "연락처 : "+position, Toast.LENGTH_SHORT).show();
    }

    public void onAddrClicked(int position) {
        Toast.makeText(getApplicationContext(), "주소 : "+position, Toast.LENGTH_SHORT).show();
    }
    public void onSexClicked(int position) {
        Toast.makeText(getApplicationContext(), "성별 : "+position, Toast.LENGTH_SHORT).show();
    }

    public void onAgeClicked(int position) {
        Toast.makeText(getApplicationContext(), "나이 : "+position, Toast.LENGTH_SHORT).show();
    }



    public void onItemLongClicked(int position) {
        Toast.makeText(getApplicationContext(), "추가화면으로이동됩니다.", Toast.LENGTH_SHORT).show();
        Intent intentAdd = new Intent(Ex28RecyclerViewFriends.this, Ex28FriendAddActivity.class);
        startActivity(intentAdd);
    }


    public static void selectFriendsList() {

        adapter.itemData.clear();

        db = helper.getReadableDatabase(); // db객체를 얻어온다. 읽기 전용
        Cursor c = db.rawQuery("SELECT * FROM members", null);

        while (c.moveToNext()) {

            int idx = c.getInt(0);
            String name = c.getString(1);
            String hp = c.getString(2);
            String sex = c.getString(3);
            String addr = c.getString(4);
            String age = c.getString(5);

            dataList.add(new Ex28ItemData(name,hp,addr,sex,Integer.parseInt(age)));

        }
        c.close();
        db.close();
        recyclerView.setAdapter(adapter);


    }

}