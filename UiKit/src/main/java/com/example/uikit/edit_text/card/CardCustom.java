package com.example.uikit.edit_text.card;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.uikit.R;

public class CardCustom extends ConstraintLayout {
    FrameLayout Frame;

    public CardCustom(@NonNull Context context) {
        super(context);
        init();
    }

    public CardCustom(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardCustom(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init()
    {
        LayoutInflater.from(getContext()).inflate(R.layout.card_background,this,true);
        Frame = findViewById(R.id.frame);
    }
}
