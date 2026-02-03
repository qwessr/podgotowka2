package com.example.podgotowka22;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import com.example.uikit.edit_text.BottomSheet.CustomBottomSheet;
import com.example.uikit.edit_text.Button.BthBig;
import com.example.uikit.edit_text.Button.BthBubbleBack;
import com.example.uikit.edit_text.Button.BthBubbleFilter;
import com.example.uikit.edit_text.Button.BthCard;
import com.example.uikit.edit_text.Button.BthChips;
import com.example.uikit.edit_text.Button.BthCustom;
import com.example.uikit.edit_text.Button.BthSmall;
import com.example.uikit.edit_text.Button.BthSocial;
import com.example.uikit.edit_text.EtDate;
import com.example.uikit.edit_text.EtPassword;
import com.example.uikit.edit_text.etDefault;
import com.example.uikit.edit_text.header.Header;
import com.example.uikit.edit_text.search.EtSearch;
import com.example.uikit.edit_text.tabbar.TabBarCustom;

import select.CustomSelect;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        CheckInternet checkInternet = new CheckInternet(this);
//
//        UserLogin Login = new UserLogin(
//                "test@test.ru",
//                "Asdfg123",
//                checkInternet,
//                new MyResponseCallback() {
//                    @Override
//                    public void onConpile(String result) {
//                        Log.d("LOGIN", "onConpile: " + result);
//                    }
//
//                    @Override
//                    public void onError(String errror) {
//                        Log.e("LOGIN", "onError: " + errror );
//                    }
//                }
//        );
//        Login.execute();
//
        /// Поиск
        EtSearch etSearch = findViewById(R.id.etSearch);
        if (etSearch != null) etSearch.init(0);

        /// Обычное пустое поле
        etDefault etDefault = findViewById(R.id.etDefault);
        if (etDefault != null) etDefault.init("", "Введите имя", "");

        /// Предзаполненное поле
        etDefault etFilled = findViewById(R.id.etFilled);
        if (etFilled != null) etFilled.init("", "Введите имя", "Иван");

        /// Поле с заголовком (Title)
        etDefault etDescription = findViewById(R.id.etDescription);
        if (etDescription != null) etDescription.init("Имя", "Введите имя", "Иван");

        /// Поле с ошибкой
        etDefault etError = findViewById(R.id.etError);
        if (etError != null) {
            etError.init("Имя", "Имя", "");
            etError.OnError(true, "Ошибка валидации");
        }

        /// Поле пароля
        EtPassword etPassword = findViewById(R.id.etPassword);
        if (etPassword != null) {
            etPassword.init("", "", "123456789");
        }

        /// Поле даты
        EtDate etDate = findViewById(R.id.etDate);
        if (etDate != null) {
            etDate.init("Дата рождения", "ДД.ММ.ГГГГ", "");
        }

        /// SELECT
        String[] Items = new String[]{"Мужской", "Женский"};
        CustomSelect select = findViewById(R.id.select);
        if (select != null) {
            select.init(Items, "Пол", "Выберите пол", null);
        }

        /// BOTTOM SHEET
        Button bthSheetDialogPreview = findViewById(R.id.bthShowDialog);
        Context context = this;
        if (bthSheetDialogPreview != null) {
            bthSheetDialogPreview.setOnClickListener(view -> {
                View view1 = LayoutInflater.from(context).inflate(com.example.uikit.R.layout.et_defualt, null);
                CustomBottomSheet.Show(context, view1, "Заголовок шторки");
            });
        }

        /// BUTTONS

        /// 1. Big Button
        BthBig bthBig = findViewById(R.id.bthBig);
        if (bthBig != null) {
            bthBig.init(0);
            bthBig.init("Главная кнопка", BthCustom.TypeButton.PRIMAPRY);
            bthBig.setOnClickListener(v -> Toast.makeText(context, "Big Button Click", Toast.LENGTH_SHORT).show());
        }

        /// 2. Small Button
        BthSmall bthSmall = findViewById(R.id.bthSmall);
        if (bthSmall != null) {
            bthSmall.init(0);
            bthSmall.init("Small", BthCustom.TypeButton.SECONDARY);
        }

        /// 3. Chips
        BthChips bthChips = findViewById(R.id.bthChips);
        if (bthChips != null) {
            bthChips.init(0);
            bthChips.init("Chips", BthCustom.TypeButton.ON);
        }

        /// 4. Card Button (Кнопка с ценой)
        BthCard bthCard = findViewById(R.id.bthCard);
        if (bthCard != null) {
            bthCard.init(0);
            bthCard.init("В корзину", BthCustom.TypeButton.PRIMAPRY);
            bthCard.onCost(1250.0);
        }

        /// 5. Social Buttons
        BthSocial bthVk = findViewById(R.id.bthVk);
        if (bthVk != null) bthVk.init(BthSocial.Type.VK);

        BthSocial bthYandex = findViewById(R.id.bthYandex);
        if (bthYandex != null) bthYandex.init(BthSocial.Type.YANDEX);

        BthBubbleBack bthBack = findViewById(R.id.bthBack);
        if (bthBack != null) bthBack.init(0);

        BthBubbleFilter bthFilter = findViewById(R.id.bthFilter);
        if (bthFilter != null) bthFilter.init(0);

        TabBarCustom tabBar = findViewById(R.id.tabBar);
        if (tabBar != null && tabBar.Adapter != null) {
            tabBar.Adapter.Listner = position -> {
                Log.e("TabBar", "Clicked position: " + position);
                Toast.makeText(context, "Tab selected: " + position, Toast.LENGTH_SHORT).show();
            };
        }

        Header header = findViewById(R.id.header);
        if (header != null) {
            header.init(
                    Header.Type.BIG,
                    com.example.uikit.R.drawable.ic_bubble_back,
                    com.example.uikit.R.drawable.ic_trash,
                    "Заголовок");

            if (header.BthLeft != null) {
                header.BthLeft.setOnClickListener(v -> {
                    Log.e("Notify", "Click Back Button");
                });
            }
        }
    }
}