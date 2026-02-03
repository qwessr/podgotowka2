package com.example.uikit.edit_text.Button;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.uikit.R;

public class BthChips extends BthCustom{
    public BthChips(@NonNull Context context) {
        super(context);
    }

    public BthChips(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BthChips(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Integer idLayot)
    {
        super.init(R.layout.bth_chips);
    }
}
