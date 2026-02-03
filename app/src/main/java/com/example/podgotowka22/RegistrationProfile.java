package com.example.podgotowka22;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.networkmodule.common.CheckInternet;
import com.example.networkmodule.common.MyResponseCallback;
import com.example.networkmodule.models.User;
import com.example.networkmodule.user.UserCreate;
import com.example.uikit.edit_text.Button.BthBig;
import com.example.uikit.edit_text.Button.BthCustom;
import com.example.uikit.edit_text.EtPassword;
import com.example.uikit.edit_text.etDefault;

import select.CustomSelect;

public class RegistrationProfile extends AppCompatActivity {

    private BthBig bthBig,bthBig2;
    private  etDefault etDefault1, etDefault2,etDefault4,etDefault3,etDate;
    private EtPassword etPassword1,etPassword2;
    private TextView error1,error2;
    private User user;

    private CheckInternet checkInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        checkInternet = new CheckInternet(this);

        etDefault1 = findViewById(R.id.etDefault1);
        etDefault2 = findViewById(R.id.etDefault2);
        bthBig = findViewById(R.id.etNext);

        if (etDefault1 != null) {
                etDefault1.init("", "Имя", "");
        }

        if (etDefault2 != null) {
            etDefault2.init("", "Отчество", "");
        }

        etDefault3 = findViewById(R.id.etDefault3);
        if (etDefault3 != null) {
            etDefault3.init("", "Фамилия", "");
        }
        etDate = findViewById(R.id.etDate);
        if (etDate != null) {
            etDate.init("", "Дата рождения", "");
        }

        /// SELECT
        String[] Items = new String[]{"Мужской", "Женский"};
        CustomSelect select = findViewById(R.id.select);
        if (select != null) {
            select.init(Items, "", "Пол", null);
        }

        etDefault4 = findViewById(R.id.etDefault4);
        if (etDefault4 != null) {
            etDefault4.init("", "Почта", "");
        }

        if (bthBig != null) {
            bthBig.init(com.example.uikit.R.layout.bth_big);
            bthBig.init("Далее", BthCustom.TypeButton.PRIMAPRY);

            if (bthBig.Bth != null) {
                bthBig.Bth.setOnClickListener(v -> {
                    Log.d("BUTTON_CLICK", "Кнопка нажата!");
                    Toast.makeText(this, "Нажата кнопка", Toast.LENGTH_SHORT).show();
                });
            }
        }

        bthBig.Bth.setOnClickListener(NextRegistration);
        bthBig.setEnabled(false);


        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean isName =
                        etDefault1.editText != null &&
                                !etDefault1.editText.getText().toString().trim().isEmpty();

                boolean isLastname =
                        etDefault2.editText != null &&
                                !etDefault2.editText.getText().toString().trim().isEmpty();

                boolean isSurname =
                        etDefault3.editText != null &&
                                !etDefault3.editText.getText().toString().trim().isEmpty();

                boolean isDataOfBirth =
                        etDate.editText != null &&
                                !etDate.editText.getText().toString().trim().isEmpty();

                boolean isGender =
                        select != null &&
                                select.textView != null &&
                                !select.textView.getText().toString().trim().isEmpty();

                boolean isEmailNotEmpty =
                        etDefault4.editText != null &&
                                !etDefault4.editText.getText().toString().trim().isEmpty();



                bthBig.setEnabled(isEmailNotEmpty && isName &&  isLastname && isSurname &&  isDataOfBirth && isGender);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        etDefault1.editText.addTextChangedListener(watcher);
        etDefault2.editText.addTextChangedListener(watcher);
        etDefault3.editText.addTextChangedListener(watcher);
        etDefault4.editText.addTextChangedListener(watcher);
        etDate.editText.addTextChangedListener(watcher);


    }
    /// Продолжить регистрацию
    View.OnClickListener NextRegistration = v -> {
        if (!bthBig.isEnabled()) return;

        user = new User();
        user.firstname = etDefault1.editText.getText().toString();

        setContentView(R.layout.registration_password);

        initPasswordScreen();
    };

    /// Конец регистрации
    View.OnClickListener EndRegistration = v -> {
        user.password = etPassword1.editText.getText().toString();

        UserCreate request = new UserCreate(
                user,
                checkInternet,
                new MyResponseCallback() {
                    @Override
                    public void onConpile(String result) {
                        // успех
                    }

                    @Override
                    public void onError(String error) {
                        // ошибка
                    }
                }
        );

        request.execute();
    };

    private void initPasswordScreen() {

        etPassword1 = findViewById(R.id.etPassword1);
        etPassword2 = findViewById(R.id.etPassword2);
        bthBig2 = findViewById(R.id.etEnd);

        if (etPassword1 != null) {
            etPassword1.init("Новый пароль", "", "");
        }

        if (etPassword2 != null) {
            etPassword2.init("Повторите пароль", "", "");
        }

        if (bthBig2 != null) {
            bthBig2.init(com.example.uikit.R.layout.bth_big);
            bthBig2.init("Сохранить", BthCustom.TypeButton.PRIMAPRY);
            bthBig2.setEnabled(false);
            bthBig2.Bth.setOnClickListener(EndRegistration);
        }

        TextWatcher passwordWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String pass1 = etPassword1.editText.getText().toString();
                String pass2 = etPassword2.editText.getText().toString();

                boolean isLengthOk = pass1.length() >= 1;
                boolean isEqual = pass1.equals(pass2);

                error2.setVisibility(isEqual || pass2.isEmpty() ? View.GONE : View.VISIBLE);

                bthBig2.setEnabled(isLengthOk && isEqual);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };


        etPassword1.editText.addTextChangedListener(passwordWatcher);
        etPassword2.editText.addTextChangedListener(passwordWatcher);

        error2 = etPassword2.findViewById(com.example.uikit.R.id.tvViewError);


    }

}
