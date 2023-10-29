package com.example.recycleview_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.recycleview_drawer.databinding.ActivityMainBinding;
import com.example.recycleview_drawer.fragment.FavorFragment;
import com.example.recycleview_drawer.fragment.HistoryFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActivityMainBinding binding;
    DrawerLayout mDrawerLayout;
    //Bước 15 khai báo biến lưu trữ trạng thái fragment
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_FAVOR = 1;
    private static final int FRAGMENT_USER = 2;
    //Bước 16 khai báo fragment trung gian (mặc định là home do mở ra là home)
    private int mCurrentFragment = FRAGMENT_HOME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        Để có thể thay đổi nhiều fragment khác nhau nên setContentView bằng getRoot()
        setContentView(binding.getRoot());
        replaceFragment(new RoomFragment());
      //  Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
//                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
//        mDrawerLayout.addDrawerListener(toggle);
//        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void replaceFragment(Fragment fragment){
//       FragmentManager quản lý các Fragment trong trong layout
//        getSupportFragmentManager trả về các tương tác của fragment đã kết hợp với activy hiện tại
        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction thực hiện việc thay đổi fragment
//        fragmentManager.beginTransaction() trình quản lý Fragment cho phép bắt đầu việc thay thế fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        replace(): thực hiện thay thế FrameLayout ban đầu bằng một fragment truyền vào
        fragmentTransaction.replace(R.id.frameLayout, fragment);
//        commit() xác nhận việc thay thế và kế thúc thay thế fragment
        fragmentTransaction.commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            if (mCurrentFragment != FRAGMENT_HOME){
                //Nếu vô id nav home nhưng frag khác home thì chuyển về home và lưu biến fragment home
                replaceFragment(new RoomFragment());
                mCurrentFragment = FRAGMENT_HOME;
            }
        }
        else if(id == R.id.nav_favor)
        {
            if (mCurrentFragment != FRAGMENT_FAVOR){
                replaceFragment(new FavorFragment());
                mCurrentFragment = FRAGMENT_FAVOR;
            }
        }
        else if(id == R.id.nav_history)
        {
            finishAffinity();
        }
        else if(id == R.id.nav_profile)
        {
            if (mCurrentFragment != FRAGMENT_USER){
                replaceFragment(new UserFragment());
                mCurrentFragment = FRAGMENT_USER;
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}