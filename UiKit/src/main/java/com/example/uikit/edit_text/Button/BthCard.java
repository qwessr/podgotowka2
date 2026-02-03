package com.example.uikit.edit_text.Button;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.uikit.R;

public class BthCard extends BthCustom{

    TextView tvCost;

    public BthCard(@NonNull Context context) {
        super(context);
    }

    public BthCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BthCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Integer idLayout) {
        super.init(R.layout.bth_card);
        tvCost=findViewById(R.id.tvCost);
    }

    public void onCost(Double value)
    {
        tvCost.setText(value.toString()+"Р");
    }
}
