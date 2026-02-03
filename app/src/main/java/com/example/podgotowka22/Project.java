package com.example.podgotowka22;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.uikit.edit_text.BottomSheet.CustomBottomSheet;
import com.example.uikit.edit_text.Button.BthBig;
import com.example.uikit.edit_text.Button.BthCustom;
import com.example.uikit.edit_text.EtDate;
import com.example.uikit.edit_text.etDefault;
import com.example.uikit.edit_text.tabbar.TabBarCustom;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import select.CustomSelect;

public class Project extends AppCompatActivity {
    public TabBarCustom tabBar;
    private static final int REQ_CAMERA = 100;
    private static final int REQ_GALLERY = 101;
    private static final int PERMISSION_REQUEST_CODE = 200;

    ImageView ivPhoto;
    BottomSheetDialog bottomSheetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_project);


        tabBar = findViewById(R.id.tabBar);
        /// SELECT
        String[] ItemsType= new String[]{"Мужской", "Женский"};
        CustomSelect selectType = findViewById(R.id.selectType);
        if (selectType != null) {
            selectType.init(ItemsType, "Тип", "Выберите тип", null);
        }

        etDefault etDefaultNameProject = findViewById(R.id.etDefaultNameProject);
        if (etDefaultNameProject != null) {
            etDefaultNameProject.init("Название проекта", "Введите имя", "");
        }

        EtDate etDateStart = findViewById(R.id.etDateStart);
        if (etDateStart != null) {
            etDateStart.init("Дата начала", "--.--.----", "");
        }

        EtDate etDateEnd= findViewById(R.id.etDateEnd);
        if (etDateEnd != null) {
            etDateEnd.init("Дата окончания", "--.--.----", "");
        }

        String[] ItemsWhon = new String[]{"Мужской", "Женский"};
        CustomSelect selectWhon = findViewById(R.id.selectWhom);
        if (selectWhon != null) {
            selectWhon.init(ItemsWhon, "Тип", "Выберите тип", null);
        }

        etDefault etDefaultEmail= findViewById(R.id.etDefaultEmail);
        if (etDefaultEmail != null) {
            etDefaultEmail.init("Источник описания", "example.com", "");
        }

        String[] ItemsCategory = new String[]{"Мужской", "Женский"};
        CustomSelect selectCategory= findViewById(R.id.selectCategory);
        if (selectCategory != null) {
            selectCategory.init(ItemsCategory, "Категория", "Выберите категорию", null);
        }

        BthBig bthBig = findViewById(R.id.bthBig);

        if (bthBig != null) {
            bthBig.init(com.example.uikit.R.layout.bth_big);
            bthBig.init("Подтвердить", BthCustom.TypeButton.PRIMAPRY);

            if (bthBig.Bth != null) {
                bthBig.Bth.setOnClickListener(v -> {
                    Log.d("BUTTON_CLICK", "Кнопка нажата!");
                    Toast.makeText(this, "Нажата кнопка", Toast.LENGTH_SHORT).show();
                });
            }
        }

        ivPhoto = findViewById(R.id.ivPhoto);

        checkPermissions();

        ivPhoto = findViewById(R.id.ivPhoto);


    }
    @Override
    protected void onResume() {
        super.onResume();
        NavigationUtil.setupTabBar(this, tabBar, 2);
    }
    public void onSelectImage(View v) {
        showSelectImageBottomSheet();
    }

    private void showSelectImageBottomSheet() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_select_image, null);

        View btnCamera = view.findViewById(R.id.cameraLayout);
        View btnGallery = view.findViewById(R.id.galleryLayout);

        if (btnCamera != null) {
            btnCamera.setOnClickListener(v -> {
                if (bottomSheetDialog != null) bottomSheetDialog.dismiss();
                openCamera();
            });
        }

        if (btnGallery != null) {
            btnGallery.setOnClickListener(v -> {
                if (bottomSheetDialog != null) bottomSheetDialog.dismiss();
                openGallery();
            });
        }

        bottomSheetDialog = CustomBottomSheet.Show(this, view, "Загрузить фото");
    }

    private void openCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, REQ_CAMERA);
            } else {
                Toast.makeText(this, "Камера недоступна", Toast.LENGTH_SHORT).show();
            }
        } else {
            checkPermissions();
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, REQ_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQ_CAMERA) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    ivPhoto.setImageBitmap(imageBitmap);
                }
            } else if (requestCode == REQ_GALLERY) {
                ivPhoto.setImageURI(data.getData());
            }
        }
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, PERMISSION_REQUEST_CODE);
        }
    }
}