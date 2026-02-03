package com.example.podgotowka22;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uikit.edit_text.Button.BthChips;
import com.example.uikit.edit_text.common.Item;
import com.example.uikit.edit_text.search.EtSearch;
import com.example.uikit.edit_text.tabbar.TabBarCustom;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {

    TabBarCustom tabBar;
    RecyclerView rvProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        EtSearch etSearch = findViewById(R.id.etSearch);
        if (etSearch != null) {
            etSearch.init2("", "Искать описания", "");
            etSearch.init(0);
        }

        setupChips();

        rvProjects = findViewById(R.id.rvProjects);
        if (rvProjects != null) {
            setupRecyclerView();
        }

        tabBar = findViewById(R.id.tabBar);
    }

    private void setupChips() {
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
    }

    private void setupRecyclerView() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Рубашка Воскресенье для машинного вязания", "Мужская одежда", 300));
        items.add(new Item("Джинсы прямого кроя", "Мужская одежда", 1200));
        items.add(new Item("Платье летнее", "Женская одежда", 1500));
        items.add(new Item("Кроссовки спортивные", "Обувь", 2500));
        items.add(new Item("Шапка зимняя", "Аксессуары", 800));
        items.add(new Item("Сумка через плечо", "Аксессуары", 1800));

        CardAdapter adapter = new CardAdapter(items);
        rvProjects.setLayoutManager(new LinearLayoutManager(this));
        rvProjects.setAdapter(adapter);

        rvProjects.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(android.graphics.Rect outRect, android.view.View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 40;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        NavigationUtil.setupTabBar(this, tabBar, 0);
    }
}