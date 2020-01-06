package dev.edmt.petaniaplikasi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Petani_MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;
    private PetaniSectionsPagerAdapter mSectionsPagerAdapter;
    private FirebaseAuth firebaseAuth;

    private TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petani__main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        mViewPager = (ViewPager) findViewById(R.id.main_tabPager);


        firebaseAuth = FirebaseAuth.getInstance();


        //Tabs
        mSectionsPagerAdapter = new PetaniSectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final TabLayout.Tab Facebook = mTabLayout.newTab();
        final TabLayout.Tab Youtube = mTabLayout.newTab();

        Facebook.setText("Beranda");
        Youtube.setText("Tentang");

        View FaceView = getLayoutInflater().inflate(R.layout.custom_tabs,null);
        ImageView face = (ImageView) FaceView.findViewById(R.id.image);
        TextView textface = (TextView) FaceView.findViewById(R.id.text_view);
        face.setImageResource(R.drawable.homeputih);
        textface.setText("Beranda");

        View YtbView = getLayoutInflater().inflate(R.layout.custom_tabs,null);
        ImageView Ytb = (ImageView) YtbView.findViewById(R.id.image);
        TextView textYtb = (TextView) YtbView.findViewById(R.id.text_view);
        Ytb.setImageResource(R.drawable.aboutputih);
        textYtb.setText("Tentang");

        Facebook.setCustomView(FaceView);
        Youtube.setCustomView(YtbView);

        mTabLayout.addTab(Facebook,0);
        mTabLayout.addTab(Youtube,1);

        mTabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicate));

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:{
                        Facebook.setIcon(R.drawable.ic_home_white_24dp);
                        Youtube.setIcon(R.drawable.ic_account_circle_black_24dp);
                        break;
                    }
                    case 1:{
                        Facebook.setIcon(R.drawable.ic_home_black_24dp);
                        Youtube.setIcon(R.drawable.ic_account_circle_white_24dp);
                        break;
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_customesservice) {
            // Handle the camera action
        }else if(id == R.id.nav_profile){

            startActivity(new Intent(Petani_MainActivity.this, profile_nav.class));


        } else if (id == R.id.nav_logout) {
            firebaseAuth.signOut();
            finish();

            startActivity(new Intent(Petani_MainActivity.this, dev.edmt.petaniaplikasi.petani_login.class));



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
