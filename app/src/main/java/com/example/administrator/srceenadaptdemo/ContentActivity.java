package com.example.administrator.srceenadaptdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout2);
        Intent i = getIntent();
        String content = i.getStringExtra("content");

        TextView textView = new TextView(this);
        textView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText(content);

        frameLayout.addView(textView);

    }
}
