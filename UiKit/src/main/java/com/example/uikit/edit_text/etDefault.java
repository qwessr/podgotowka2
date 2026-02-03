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

public class etDefault extends ConstraintLayout {

    public EditText editText;
    public TextView textView;
    private boolean onError = false;

    public etDefault(@NonNull Context context) {
        super(context);
        setupUI();
    }

    public etDefault(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupUI();
    }

    public etDefault(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupUI();
    }

    private void setupUI() {
        LayoutInflater.from(getContext()).inflate(R.layout.et_defualt, this, true);
        editText = findViewById(R.id.et_edit_text);
        textView = findViewById(R.id.tvTextView);

        if (editText != null) {
            editText.setOnFocusChangeListener(FocusListener);
        }
    }

    public void init(String title, String hint, String text) {
        if (textView != null) {
            if (title == null || title.isEmpty()) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setText(title);
                textView.setVisibility(View.VISIBLE);
            }
        }

        if (editText != null) {
            editText.setHint(hint);
            editText.setText(text);
        }
    }

    public void init(String title, String hint) {
        init(title, hint, "");
    }

    private OnFocusChangeListener FocusListener = (v, hasFocus) -> setState();

    public void OnError(boolean state, String errorText) {
        this.onError = state;
        setState();
    }

    protected void setState() {
        if (editText == null) return;

        if (onError) {
            editText.setBackgroundResource(R.drawable.et_state_error);
        } else {
            if (String.valueOf(editText.getText()).isEmpty()) {
                editText.setBackgroundResource(R.drawable.et_stste_defualt);
            } else {
                editText.setBackgroundResource(R.drawable.et_state_filled);
            }
        }
    }
}