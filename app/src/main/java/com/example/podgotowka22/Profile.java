package com.example.podgotowka22;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uikit.edit_text.tabbar.TabBarCustom;

import toggle.Toggle;

public class Profile extends AppCompatActivity {

    public TabBarCustom tabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Toggle toggle = findViewById(R.id.toggle);
        toggle.setChecked(true);

        Log.d("TOGGLE", "State: " + toggle.isChecked());

        tabBar = findViewById(R.id.tabBar);
        NavigationUtil.setupTabBar(this, tabBar, 3);


        tabBar = findViewById(R.id.tabBar);
    }
    @Override
    protected void onResume() {
        super.onResume();
        NavigationUtil.setupTabBar(this, tabBar, 3);
    }
}
