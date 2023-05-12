package com.cms.mystudyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ex20FriendDelActivity extends AppCompatActivity {
    TextView tvHp,tvSex, tvAddr, tvAge;
    EditText etName;

    Button btnDel, btnFind;
    //전달할 변수들
    String sendId, sendPw, sendName, sendHp, selectSex="남자";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex20_del);


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
                    Toast.makeText(Ex20FriendDelActivity.this,"이름을 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }

                for(int i=0; i<Ex20FriendsListActivity.adapter.getCount();i++)
                {
                    //Log.d("아이템값체크","아아템:"+Ex20FriendsListActivity.adapter.items.get(i).getName());
                    if(findName.equals(Ex20FriendsListActivity.adapter.items.get(i).getName()))
                    {
                        //값자리 초기화
                        etName.setText(Ex20FriendsListActivity.adapter.items.get(i).getName());
                        tvHp.setText(Ex20FriendsListActivity.adapter.items.get(i).getHp());
                        tvAddr.setText(Ex20FriendsListActivity.adapter.items.get(i).getAddr());
                        tvAge.setText(Ex20FriendsListActivity.adapter.items.get(i).getAge()+"");
                        tvSex.setText(Ex20FriendsListActivity.adapter.items.get(i).getSex());
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
                    Toast.makeText(Ex20FriendDelActivity.this,"이름을 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }
                for(int i=0; i<Ex20FriendsListActivity.adapter.getCount();i++)
                {
                    //Log.d("아이템값체크","아아템:"+Ex20FriendsListActivity.adapter.items.get(i).getName());
                    if(findName.equals(Ex20FriendsListActivity.adapter.items.get(i).getName()))
                    {
                        Ex20FriendsListActivity.adapter.items.remove(i);
                        break;
                    }
                }
                //메인에 있는 리스트뷰에 변화가 생겨서 갱신.
                Ex20FriendsListActivity.adapter.notifyDataSetChanged();

                //값자리 초기화
                etName.setText("");
                tvHp.setText("");
                tvAddr.setText("");
                tvSex.setText("");
                tvAge.setText("");

                Toast.makeText(Ex20FriendDelActivity.this,"삭제완료!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}