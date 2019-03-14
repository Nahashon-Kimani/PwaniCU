package com.peter.homeFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import com.peter.adapter.NoticeAdapter;
import com.peter.adapter.NoticeContract;

import java.util.ArrayList;

public class Notice extends Fragment {
    View view;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    BottomNavigationView navigationView;
    NoticeContract noticeContract;
    String category;
    ArrayList<NoticeContract> list;
    RecyclerView notices;

    public Notice() {
    }

    public void onCreate(Bundle savedIstanceState){
        super.onCreate(savedIstanceState);
        list=new ArrayList<>();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.notice_fragment, container, false);


        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Notice");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    list.add(snapshot.getValue(NoticeContract.class));




                }
                notices = view.findViewById(R.id.notice_recycler);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                notices.setLayoutManager(manager);
                notices.setAdapter(new NoticeAdapter(getContext(),list));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        f();
        //getting the Notices from FireBase
        //Adapter and ArrayList should be created to add all the items

        return view;
    }

    public void f(){
        navigationView = view.findViewById(R.id.event_notice_btm_bar);
        navigationView.setItemIconTintList(null);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.category_event:
                        //load only events with category events only.
                        category = noticeContract.getCategory();
                        Toast.makeText(getContext(), "Events", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.category_notice:
                        //load only notices
                        Toast.makeText(getContext(), "Notices", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.category_all:
                        Toast.makeText(getContext(), "All notices and evens combined", Toast.LENGTH_SHORT).show();
                        //load all notices and events combined
                        break;
                    default:
                        break;

                }

                return true;
            }
        });
    }
}
