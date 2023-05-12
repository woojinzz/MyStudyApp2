package com.cms.mystudyapp;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Ex19PhoneCreate2ListView extends AppCompatActivity {
    private String TAG = "리스트뷰예제";
    private ListView listview = null;
    private ListViewAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex19_listview);

        listview = (ListView) findViewById(R.id.listview);
        adapter = new ListViewAdapter();

        //Adapter 안에 아이템의 정보 담기
        adapter.addItem(new Ex19honeItem("홍길동1", "01011112221", R.drawable.tel));
        adapter.addItem(new Ex19honeItem("홍길동2", "01011112222", R.drawable.tel));
        adapter.addItem(new Ex19honeItem("홍길동3", "01011112223", R.drawable.tel));
        adapter.addItem(new Ex19honeItem("홍길동4", "01011112224", R.drawable.tel));
        adapter.addItem(new Ex19honeItem("홍길동5", "01011112225", R.drawable.tel));
        adapter.addItem(new Ex19honeItem("홍길동6", "01011112226", R.drawable.tel));
        adapter.addItem(new Ex19honeItem("홍길동7", "01011112227", R.drawable.tel));
        adapter.addItem(new Ex19honeItem("홍길동8", "01011112228", R.drawable.tel));

        //리스트뷰에 Adapter 설정
        listview.setAdapter(adapter);
    }


    /* 리스트뷰 어댑터 */
    public class ListViewAdapter extends BaseAdapter {
        ArrayList<Ex19honeItem> items = new ArrayList<Ex19honeItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(Ex19honeItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            final Context context = viewGroup.getContext();
            final Ex19honeItem phoneItem = items.get(position);

            if(convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_ex19_listview_list_item, viewGroup, false);

            } else {
                View view = new View(context);
                view = (View) convertView;
            }

            TextView tv_name = (TextView) convertView.findViewById(R.id.tvName);
            TextView tv_num = (TextView) convertView.findViewById(R.id.tvPhone);
            ImageView iv_icon = (ImageView) convertView.findViewById(R.id.ivTel);

            tv_num.setText(phoneItem.getNum());
            tv_name.setText(phoneItem.getName());
            iv_icon.setImageResource(phoneItem.getResId());
            Log.d(TAG, "getView() - [ "+position+" ] "+phoneItem.getName());

            //각 아이템 선택 event
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    Toast.makeText(getApplicationContext(),"R.id.iv_icon:"+R.id.iv_icon,Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(),"phoneItem.getResId():"+phoneItem.getResId(),Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),phoneItem.getNum()+"에게 전화를 겁니다!",Toast.LENGTH_SHORT).show();
                    Intent intentTel = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+phoneItem.getNum()));
                    startActivity(intentTel);
                }
            });

            return convertView;  //뷰 객체 반환
        }
    }

    }