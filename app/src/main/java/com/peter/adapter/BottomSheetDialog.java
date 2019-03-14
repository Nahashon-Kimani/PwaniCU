package com.peter.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.peter.R;

import java.util.Calendar;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    View view;
    TextView todayDay, todayProgram;
    DatabaseReference mRef;
    Calendar calendar;
    int day;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.today_program, container, false);

        todayDay = view.findViewById(R.id.today_day);
        todayProgram = view.findViewById(R.id.today_program);

        //getting the exact day of the week
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_WEEK);
        String exactDay = Integer.toString(day);

        //Getting the Program of the day from FireBase Database.
        mRef = FirebaseDatabase.getInstance().getReference().child("Today Program").child(exactDay);

        //convert the day from 1,2,3,4,5,6,7 to their respective days Sunday, Monday, Tuesday.

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(getContext(), dataSnapshot.getKey().toString(), Toast.LENGTH_SHORT).show();
                todayProgram.setText(dataSnapshot.getValue().toString());

                switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                    case 1:
                        todayDay.setText(getResources().getString(R.string.sun));
                        break;
                    case 2:
                        todayDay.setText(getResources().getString(R.string.mon));
                        break;
                    case 3:
                        todayDay.setText(getResources().getString(R.string.tue));
                        break;
                    case 4:
                        todayDay.setText(getResources().getString(R.string.wed));
                        break;
                    case 5:
                        todayDay.setText(getResources().getString(R.string.thu));
                        break;
                    case 6:
                        todayDay.setText(getResources().getString(R.string.fri));
                        break;
                    case 7:
                        todayDay.setText(getResources().getString(R.string.sat));
                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "An error occurred. Please try again later", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}