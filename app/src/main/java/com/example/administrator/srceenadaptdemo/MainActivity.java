package com.example.administrator.srceenadaptdemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Fragment> fragments;
    private String[] titles = {"北京遇上西雅图","变形金刚","百万富翁的初恋","寒战2"};
    private int index = 0;
    private FragmentManager fm;
    private Fragment f;
    private MyFragment fragment;
    private Boolean flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fm = getSupportFragmentManager();

        initFragment();
        f = fragments.get(0);
         //横屏的时候
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){

        //竖屏的时候
        }else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            DeaultFragment();
        }

        initListView();


    }

     private void DeaultFragment() {

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frameLayout,fragments.get(0));
        transaction.commit();

    }


    private void initListView() {
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titles);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(MainActivity.this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT ){
                    String content = titles[position];
                    Intent i = new Intent(MainActivity.this,ContentActivity.class);
                    i.putExtra("content",content);
                    startActivity(i);
                }else if(MainActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    index = position;
                    if(fragments.get(index).isAdded()){
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.hide(f);
                        transaction.show(fragments.get(index));
                        transaction.commit();
                        f = fragments.get(index);
                    }else{
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.hide(f);
                        transaction.add(R.id.frameLayout,fragments.get(index));
                        transaction.commit();
                        f = fragments.get(index);
                    }
                }
            }
        });
    }

    private void initFragment() {

        fragments = new ArrayList<>();

        for (int i = 0; i <titles.length ; i++) {
            fragment = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title",titles[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
    }
}
