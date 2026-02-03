package com.example.uikit.edit_text.Button;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.uikit.R;

import java.lang.reflect.Type;

public class BthSocial extends BthCustom{

    public enum Type{
        VK,YANDEX
    }

    public BthSocial(@NonNull Context context) {
        super(context);
    }

    public BthSocial(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BthSocial(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public void init(Type type) {
        int IdLayout=0;
        if (type == Type.VK) IdLayout = R.layout.bth_vk;
        else if (type == Type.YANDEX) IdLayout = R.layout.bth_yandex;

        super.init(IdLayout);
    }
}
