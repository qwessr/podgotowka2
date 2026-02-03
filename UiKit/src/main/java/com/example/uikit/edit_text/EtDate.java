package com.example.uikit.edit_text;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.uikit.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EtDate extends CustomEditText {

    Pattern Regex = Pattern.compile("^\\d{2}\\.\\d{2}\\.\\d{4}$");

    public EtDate(@NonNull Context context) {
        super(context);
    }
    public EtDate(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }
    public EtDate(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Integer idLayout) {
        super.init(R.layout.et_date);


            editText.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        String value = String.valueOf(editText.getText());
                        Matcher match = Regex.matcher(value);

                        if (!match.find()) {
                            EtDate.super.OnError(true, "Введите дату");
                        } else {
                            EtDate.super.OnError(false, "");
                        }
                    }
                }
            });

    }
}