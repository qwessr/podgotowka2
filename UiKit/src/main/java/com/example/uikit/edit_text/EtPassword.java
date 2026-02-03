package com.example.uikit.edit_text;

import android.content.Context;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.uikit.R;

public class EtPassword extends ConstraintLayout {

    public EditText editText;
    public TextView textView;
    public ImageView bthPassword;

    public EtPassword(@NonNull Context context) {
        super(context);
        init();
    }

    public EtPassword(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EtPassword(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.et_password, this, true);
        editText = findViewById(R.id.et_edit_text);
        textView = findViewById(R.id.textView);
        bthPassword = findViewById(R.id.bthPassword);

        if (bthPassword != null && editText != null) {
            bthPassword.setOnClickListener(v -> {
                if (editText.getTransformationMethod() == null) {
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    bthPassword.setImageResource(R.drawable.ic_password_hide);
                } else {
                    editText.setTransformationMethod(null);
                    bthPassword.setImageResource(R.drawable.ic_password_show);
                }
                editText.setSelection(editText.getText().length());
            });
        }
    }

    public void init(String title, String hint, String text) {
        if (textView != null) {
            textView.setText(title);
            textView.setVisibility(title.isEmpty() ? View.GONE : View.VISIBLE);
        }
        if (editText != null) {
            editText.setHint(hint);
            editText.setText(text);
        }
    }
}