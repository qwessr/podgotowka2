package com.example.uikit.edit_text.tabbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uikit.R;

import java.util.ArrayList;
import java.util.List;

public class TabBarCustom extends ConstraintLayout {

    public RecyclerView RecyclerView;

    public TabBarAdapter Adapter;

    public TabBarCustom(@NonNull Context context) {
        super(context);
        init();
    }

    public TabBarCustom(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TabBarCustom(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.tabbar,this,true);

        List<TabBarItem> Items= new ArrayList<TabBarItem>();
        Items.add(new TabBarItem("Главная",R.drawable.ic_home));
        Items.add(new TabBarItem("Каталог",R.drawable.ic_catalog));
        Items.add(new TabBarItem("Проекты",R.drawable.ic_project));
        Items.add(new TabBarItem("Профиль",R.drawable.ic_profile));

        RecyclerView = findViewById(R.id.recyleView);
        RecyclerView.setLayoutManager(new GridLayoutManager(getContext(),Items.size()));

        Adapter = new TabBarAdapter(Items);
        RecyclerView.setAdapter(Adapter);

    }
    public void setOnTabSelected(OnTabSelected listener) {
        if (Adapter == null) return;

        Adapter.Listner = listener::onSelect;
    }

    public interface OnTabSelected {
        void onSelect(int position);
    }
    public void setActiveTab(int position) {
        if (Adapter == null) return;
        Adapter.setActive(position);
    }

}
