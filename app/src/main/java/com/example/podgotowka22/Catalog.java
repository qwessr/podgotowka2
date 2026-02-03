package com.example.podgotowka22;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uikit.edit_text.Button.BthChips;
import com.example.uikit.edit_text.search.EtSearch;
import com.example.uikit.edit_text.tabbar.TabBarCustom;

public class Catalog extends AppCompatActivity {
    TabBarCustom tabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog);

        EtSearch etSearch = findViewById(R.id.etSearch);
        if (etSearch != null) etSearch.init2("","Искать описания","");
        etSearch.init(0);
        BthChips all = findViewById(R.id.bthChipsAll);
        BthChips y = findViewById(R.id.bthChipsY);
        BthChips x = findViewById(R.id.bthChipsX);
        BthChips kids = findViewById(R.id.bthChipsKids);
        BthChips acc = findViewById(R.id.bthChipsAccessories);

        if (all != null) {
            all.init(0);
            all.init("Все", null);
        }

        if (y != null) {
            y.init(0);
            y.init("Женщинам", null);
        }

        if (x != null) {
            x.init(0);
            x.init("Мужчинам", null);
        }

        if (kids != null) {
            kids.init(0);
            kids.init("Детям", null);
        }

        if (acc != null) {
            acc.init(0);
            acc.init("Аксессуары", null);
        }


        tabBar = findViewById(R.id.tabBar);
    }
    @Override
    protected void onResume() {
        super.onResume();
        NavigationUtil.setupTabBar(this, tabBar, 1);
    }
}
