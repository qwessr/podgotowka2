package com.example.uikit.edit_text.header;

import android.content.Context;
import android.icu.number.IntegerWidth;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.uikit.R;

public class Header extends ConstraintLayout {
    public enum Type {
        BIG,SMALL
    }
    public ImageView BthLeft, BthRight;

    TextView tvTitle;

    public Header(@NonNull Context context) {
        super(context);
    }

    public Header(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Header(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Type type, Integer idResourceBthLeft, Integer idResourceBthRight, String title){
        Integer IdLayout = type == Type.BIG ? R.layout.header_big : R.layout.header_small;
        LayoutInflater.from(getContext()).inflate(IdLayout,this, true);

        BthLeft= findViewById(R.id.bthLeft);
        BthRight= findViewById(R.id.bthRight);
        tvTitle = findViewById(R.id.tvTitle);

        if(idResourceBthLeft !=null) BthLeft.setBackgroundResource(idResourceBthLeft);
        else BthLeft.setVisibility(View.GONE);

        if(idResourceBthRight !=null) BthRight.setBackgroundResource(idResourceBthRight);
        else BthRight.setVisibility(View.GONE);

        tvTitle.setText(title);
    }
}
