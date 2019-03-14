package com.peter.homeFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.peter.R;
import com.peter.adapter.TodaySermon;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Today extends Fragment {
    View view;
    TodaySermon adapter;
    TextView verse,narrtion,thoughtOfTheDay,prayerOfThDay, todayDate;

    public Today() {
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        adapter = getArguments().getParcelable("sermon");


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.today_helper, container, false);
        verse = view.findViewById(R.id.today_verse);
        narrtion = view.findViewById(R.id.today_narration);
        thoughtOfTheDay = view.findViewById(R.id.today_thought);
        prayerOfThDay = view.findViewById(R.id.today_prayer);
        todayDate = view.findViewById(R.id.today_date);

        Date d = new Date();
        String md =  new SimpleDateFormat("yyyy-MM-dd").format(d);
        todayDate.setText(md);

        verse.setText(adapter.getVerse());
        narrtion.setText(adapter.getVerseNarration());
        thoughtOfTheDay.setText(adapter.getThoughtOfDay());
        prayerOfThDay.setText(adapter.getPrayerOfDay());




        return view;
    }

}
