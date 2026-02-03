package com.example.uikit.edit_text.Button;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.uikit.R;

public class BthBubbleBack extends BthCustom{


    public BthBubbleBack(@NonNull Context context) {
        super(context);
    }

    public BthBubbleBack(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BthBubbleBack(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Integer idLayout)
    {
        super.init(R.layout.bth_buuble_back);
    }
}
