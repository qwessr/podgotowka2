package com.example.uikit.edit_text;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.uikit.R;

/// Компонент input (ввод данных)
public class CustomEditText extends ConstraintLayout {

    /// Название поля
    public EditText editText;
    /// Текстовое поле
    public TextView textView;
    /// Состояние ошибки
    public boolean onError = false;
    /// Подсказка по заполнению
    public TextView errorTextView;

    public CustomEditText(@NonNull Context context) {
        super(context);
        init(null);
    }

    public CustomEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(null);
    }

    public CustomEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(null);
    }

    public void init(Integer idLayout) {
        if (idLayout == null) return;

        LayoutInflater.from(getContext()).inflate(idLayout, this, true);
        editText = findViewById(R.id.et_edit_text);
        textView = findViewById(R.id.textView);
        errorTextView = findViewById(R.id.tvViewError);

        if (editText != null) {
            editText.setOnFocusChangeListener(FocusListner);
        }
    }

    /// Инициализация компонента, назначение названия поля и hint
    public void init(String title, String hint, String text) {
        if (title.isEmpty()) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(title);
            textView.setVisibility(View.VISIBLE);
        }

        editText.setHint(hint);
        editText.setText(text);
    }

    /// Изменение состояния в зависимости от того что находится в текстовом поле
    private OnFocusChangeListener FocusListner = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            setState();
        }
    };

    /// Состояние ошибочного значения
    public void OnError(boolean state, String textError) {
        if (errorTextView != null) {
            errorTextView.setText(textError);
        }
        onError = state;
        setState();
    }

    /// Изменение состояния
    public void setState() {
        if (onError) {
            editText.setBackgroundResource(R.drawable.et_state_error);
            if (errorTextView != null) {
                errorTextView.setVisibility(VISIBLE);
            }
        } else {
            if (errorTextView != null) {
                errorTextView.setVisibility(INVISIBLE);
            }
            editText.setBackgroundResource(R.drawable.et_selector);
        }
    }
}