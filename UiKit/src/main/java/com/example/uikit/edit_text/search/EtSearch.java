package com.example.uikit.edit_text.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.uikit.R;
import com.example.uikit.edit_text.CustomEditText;

public class EtSearch extends CustomEditText {

    ImageView BthClear;

    public EtSearch(@NonNull Context context) {
        super(context);
    }

    public EtSearch(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EtSearch(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /// Инициализация компонента, назначение названия поля и hint
    public void init2(String title,String hint, String text) {

        if (title.isEmpty()) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(title);
            textView.setVisibility(View.VISIBLE);
        }

        editText.setHint(hint);
        editText.setText(text);

    }

    @Override
    public void init (Integer idLayout)
    {
        super.init(R.layout.et_search);

        BthClear = findViewById(R.id.bthClear);

        BthClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                BthClear.setVisibility(View.INVISIBLE);
            }
        });

        editText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP)
                {
                    if (editText.getText().toString().isEmpty())
                    {
                        BthClear.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        BthClear.setVisibility(View.VISIBLE);
                    }
                }
                return false;
            }
        });

    }
}
