package com.peter.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.peter.R;
import com.peter.adapter.NewSemesterThemeAdapter;

public class ThemeVerse extends AppCompatActivity {
    TextView semTheme, semNarration, semVersion, semName, semYear;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_verse);

        semTheme = findViewById(R.id.sem_theme);
        semNarration = findViewById(R.id.sem_narration);
        semVersion = findViewById(R.id.sem_version);
        semName = findViewById(R.id.sem_name);
        semYear = findViewById(R.id.sem_year);

        mRef = FirebaseDatabase.getInstance().getReference("Semester Theme");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    NewSemesterThemeAdapter semesterThemeAdapter = snapshot.getValue(NewSemesterThemeAdapter.class);
                    semTheme.setText(semesterThemeAdapter.getSemesterVerse());
                    semNarration.setText(semesterThemeAdapter.getSemesterNarration());
                    semVersion.setText(semesterThemeAdapter.getSemesterVersion());
                    semName.setText(semesterThemeAdapter.getSemester());
                    semYear.setText(semesterThemeAdapter.getYear());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ThemeVerse.this, "Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}