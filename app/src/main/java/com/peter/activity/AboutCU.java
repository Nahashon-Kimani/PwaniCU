package com.peter.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.peter.R;

public class AboutCU extends AppCompatActivity {
    Button readMoreAboutCU;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_cu);

        readMoreAboutCU = findViewById(R.id.read_more_about_cu);
        fab = findViewById(R.id.share_fab);


        readMoreAboutCU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebsite();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.about_pucu));
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share About PUCU"));
            }
        });
    }

    //This method is used to open the CU Website
    public  void openWebsite(){
        Uri uri = Uri.parse("http://pwanicu.org/index.php?r=site%2Flogin");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
