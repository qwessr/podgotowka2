package com.example.uikit.edit_text.Button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.uikit.R;

import kotlin.jvm.internal.KTypeBase;

public class BthCustom extends ConstraintLayout {
    public Button Bth;

    public enum TypeButton{
        PRIMAPRY, TETRIARY, SECONDARY,OFF,ON
    }

    public BthCustom(@NonNull Context context) {
        super(context);
    }

    public BthCustom(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BthCustom(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void init(Integer idLayout){
        if (idLayout==null|| idLayout == 0) return;
        this.removeAllViews();
        LayoutInflater.from(this.getContext()).inflate(idLayout,this,true);
        Bth = findViewById(R.id.bth);
    }

    public  void init(String value ,TypeButton type) {
        Bth.setText(value);

        if (type == TypeButton.PRIMAPRY || type == TypeButton.ON)
        {
            Bth.setBackgroundResource(R.drawable.bth_primary);
            Bth.setTextColor(Color.parseColor("#ffffff"));
        }
        if (type == TypeButton.SECONDARY)
        {
            Bth.setBackgroundResource(R.drawable.bth_secondary);
            Bth.setTextColor(Color.parseColor("#1a6fee"));
        }
        if (type == TypeButton.TETRIARY)
        {
            Bth.setBackgroundResource(R.drawable.bth_tetriary);
            Bth.setTextColor(Color.parseColor("#2d2c2c"));
        }
        if (type == TypeButton.OFF)
        {
            Bth.setBackgroundResource(R.drawable.bth_tetriary);
            Bth.setTextColor(Color.parseColor("#7e7e9a"));
            this.setEnabled(false);
        }
    }


    public void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);
        Bth.setEnabled(enabled);
    }
}
