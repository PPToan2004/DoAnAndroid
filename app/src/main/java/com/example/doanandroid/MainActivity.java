package com.example.doanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.doanandroid.fragment.ContactFragment;
import com.example.doanandroid.fragment.FavoriteFragment;
import com.example.doanandroid.fragment.HomeFragment;
import com.example.doanandroid.fragment.ImageFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
//test git
private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);

        //Khoi tao menu
        initMenu();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return true;
    }

    void initMenu() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Menu Drawer");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fmNew;
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    fmNew = new HomeFragment();
                    loadFragment(fmNew);
                    return true;
                } else if (itemId == R.id.nav_image) {
                    fmNew = new ImageFragment();
                    loadFragment(fmNew);
                    return true;
                } else if (itemId == R.id.nav_contact) {
                    fmNew = new ContactFragment();
                    loadFragment(fmNew);
                    return true;
                } else if (itemId == R.id.nav_favorite) {
                    fmNew = new FavoriteFragment();
                    loadFragment(fmNew);
                    return true;
                }
//                switch (item.getItemId())
//                {
//                    case :
//                        fmNew = new HomeFragment();
//                        loadFragment(fmNew);
//                        return true;
//                    case :
//                        fmNew = new ImageFragment();
//                        loadFragment(fmNew);
//                        return true;
//                    case :
//                        fmNew = new ContactFragment();
//                        loadFragment(fmNew);
//                        return true;
//                    case :
//                        fmNew = new FavoriteFragment();
//                        loadFragment(fmNew);
//                        return true;
//                }
                return true;
            }
        });
    }

    void loadFragment(Fragment fmNew)
    {
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.main_fragment, fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
        //Thu nho drawer
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}