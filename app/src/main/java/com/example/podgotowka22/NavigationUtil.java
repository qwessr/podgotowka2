package com.example.podgotowka22;

import android.app.Activity;
import android.content.Intent;

import com.example.uikit.edit_text.tabbar.TabBarCustom;

public class NavigationUtil {

    public static void setupTabBar(Activity activity, TabBarCustom tabBar, int currentTabPosition) {
        if (tabBar == null) return;

        tabBar.setActiveTab(currentTabPosition);

        tabBar.setOnTabSelected(position -> {
            if (position == currentTabPosition) return;

            Intent intent = null;
            switch (position) {
                case 0:
                    intent = new Intent(activity, Main.class);
                    break;
                case 1:
                    intent = new Intent(activity, Catalog.class);
                    break;
                case 2:
                    intent = new Intent(activity, Project.class);
                    break;
                case 3:
                    intent = new Intent(activity, Profile.class);
                    break;
            }

            if (intent != null) {
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                activity.startActivity(intent);
                activity.overridePendingTransition(0, 0);
            }
        });
    }
}