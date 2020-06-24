package com.example.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private LibraryFragment libraryFragment;
    private SearchFragment searchFragment;
    private AccountFragment accountFragment;
    private FrameLayout mainframe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        libraryFragment = new LibraryFragment();
        searchFragment = new SearchFragment();
        accountFragment = new AccountFragment();

        mainframe = findViewById(R.id.contentLayout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BottomNavigationView bt = findViewById(R.id.bottom_nav);
        bt.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedfragment = null;

            switch (menuItem.getItemId()){
                case R.id.nav_home:

                    selectedfragment = new HomeFragment();
                    getSupportActionBar().setTitle("Home");
                    //HomeFragment homeFragment = new HomeFragment();
                    //FragmentManager manager1 = getFragmentManager();
                    //manager1.beginTransaction().replace(R.id.contentLayout,homeFragment, homeFragment,false).commit();

                    break;
                case R.id.nav_lib:
                    selectedfragment = new LibraryFragment();
                    getSupportActionBar().setTitle("Library");
                    break;
                case R.id.nav_search:
                    selectedfragment = new SearchFragment();
                    getSupportActionBar().setTitle("Search");
                    break;
                case R.id.nav_acc:
                    selectedfragment = new AccountFragment();
                    getSupportActionBar().setTitle("Account");
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout,selectedfragment).commit();
            return true;
        }
    };
}
