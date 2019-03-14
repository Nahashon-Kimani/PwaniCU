package com.peter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.peter.activity.AboutCU;
import com.peter.activity.ThemeVerse;
import com.peter.adapter.BottomSheetDialog;
import com.peter.adapter.TodaySermon;
import com.peter.adapter.ViewPagerAdapter;
import com.peter.homeFragment.Discuss;
import com.peter.homeFragment.Notice;
import com.peter.homeFragment.Today;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    DrawerLayout drawer;
    NavigationView navigationView;
    ImageView todayProgramViewer;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout_id);
        viewPager = findViewById(R.id.view_pager_id);
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //FireBase
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Today");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TodaySermon sermon = new TodaySermon();
                sermon = dataSnapshot.getValue(TodaySermon.class);

                Bundle todaySermon = new Bundle();
                todaySermon.putParcelable("sermon", sermon);
                Today today = new Today();
                today.setArguments(todaySermon);
                adapter.addFragment(today, "Today");
                adapter.addFragment(new Notice(), "Notice");
                adapter.addFragment(new Discuss(), "Discuss");

                //setting up adapter
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //Adding the fragments
//        adapter.addFragment(new Today(), "Today");
//        adapter.addFragment(new Notice(), "Notice");
//        adapter.addFragment(new Discuss(), "Discuss");

        //setting up adapter
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //Setting the toolBar;
        Toolbar toolbar = findViewById(R.id.tool_bar_id);
        setSupportActionBar(toolbar);

        //Setting Up the drawer and the drawerToggle
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        //setting the OnClick Listener to Navigation Vire
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null); //This code makes icon colored.

        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        /*//This is the image view at the top there opening the bottom Sheet
        todayProgramViewer = findViewById(R.id.today_program_viewer);
        todayProgramViewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                Toast.makeText(MainActivity.this, Integer.toString(day), Toast.LENGTH_SHORT).show();

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
                bottomSheetDialog.show(getSupportFragmentManager(), "todayProgramBottomSheet");
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.about_cu:
                startActivity(new Intent(MainActivity.this, AboutCU.class));
                break;
            case R.id.theme_verse:
                startActivity(new Intent(MainActivity.this, ThemeVerse.class));
                break;
            case R.id.bs_registration:
                Toast.makeText(MainActivity.this, "Bible Study Registration", Toast.LENGTH_SHORT).show();
                break;
            case R.id.social_media:
                Toast.makeText(MainActivity.this, "Meet Us on Social Media", Toast.LENGTH_SHORT).show();
                break;
            case R.id.help_and_feedback:
                shareFeedback();
                break;
            case R.id.share_app:
                shareApp();
                break;
            case R.id.app_settings:
                Toast.makeText(MainActivity.this, "App Settings Still under development", Toast.LENGTH_SHORT).show();
                break;
            case R.id.night_mode:
                Toast.makeText(MainActivity.this, "Ignite Night mode. Still under development", Toast.LENGTH_SHORT).show();
                break;

        }

        return true;
    }


    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.today_program_viewer:
                todayProgramView();
                break;
            case R.id.app_settings:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public void todayProgramView() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        Toast.makeText(MainActivity.this, Integer.toString(day), Toast.LENGTH_SHORT).show();

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
        bottomSheetDialog.show(getSupportFragmentManager(), "todayProgramBottomSheet");
    }

    //This method for sharing the whole app. The link will be modified once the app is hosted.
    public void shareApp() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND)
                .putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.url1));
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, "This is test"));
    }

    //This method is used to send the feedback of the users via email
    public void shareFeedback(){
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "nahashonmbuci@gmail.com", null));
        intent.putExtra(Intent.EXTRA_SUBJECT, "PUCU APP FEEDBACK");
        //intent.putExtra(Intent.EXTRA_CC, "nahashonmbuci@gmail.com");
        intent.putExtra(Intent.EXTRA_TEXT, "Please explain your issue");
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(Intent.createChooser(intent, "Send Email" ));
        }
    }

    //Toggle Button to set the Night and Day mode
    public void setDayAndNightMode(){

    }
}