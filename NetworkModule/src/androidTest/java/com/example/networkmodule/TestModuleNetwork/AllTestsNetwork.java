package com.example.networkmodule.TestModuleNetwork;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.networkmodule.common.CheckInternet;
import com.example.networkmodule.common.MyResponseCallback;
import com.example.networkmodule.basket.*;
import com.example.networkmodule.models.*;
import com.example.networkmodule.order.OrderOrder;
import com.example.networkmodule.product.*;
import com.example.networkmodule.project.*;
import com.example.networkmodule.stock.StockGet;
import com.example.networkmodule.user.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AllTestsNetwork {

    private static final String TAG = "SwaggerTests";
    // Статические переменные, чтобы токен не терялся между тестами
    private static String SAVED_TOKEN = null;
    private static String SAVED_EMAIL = null;
    private static final String COMMON_PASSWORD = "Password123!";

    private Context getCtx() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    private CheckInternet getNet() {
        return new CheckInternet(getCtx());
    }

    // --- 1. АВТОРИЗАЦИЯ И ПОЛУЧЕНИЕ ТОКЕНА ---
    @Test
    public void test01_Auth_CreateAndLogin() throws InterruptedException {
        // Уникальный email, чтобы не было ошибки "Пользователь уже существует"
        SAVED_EMAIL = "test_" + System.currentTimeMillis() + "@swagger.com";
        Log.d(TAG, ">>> STEP 1: Регистрация " + SAVED_EMAIL);

        User newUser = new User();
        newUser.email = SAVED_EMAIL;
        newUser.password = COMMON_PASSWORD;
        newUser.firstname = "Test";
        newUser.lastname = "User";
        newUser.surname = "Testovich";
        newUser.dateOfBirth = "2000-01-01T00:00:00";
        newUser.gender = 1;

        CountDownLatch latch = new CountDownLatch(1);
        final boolean[] success = {false};

        new UserCreate(newUser, getNet(), new MyResponseCallback() {
            @Override
            public void onConpile(String response) {
                Log.d(TAG, "UserCreate OK: " + response);
                success[0] = true;
                latch.countDown();
            }
            @Override
            public void onError(String error) {
                Log.e(TAG, "UserCreate FAIL: " + error);
                latch.countDown();
            }
        }).execute();
        latch.await(10, TimeUnit.SECONDS);
        assertTrue("UserCreate failed", success[0]);

        Log.d(TAG, ">>> STEP 2: Логин");
        CountDownLatch latchLogin = new CountDownLatch(1);

        new Login(SAVED_EMAIL, COMMON_PASSWORD, getNet(), new MyResponseCallback() {
            @Override
            public void onConpile(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    if (json.has("token")) {
                        SAVED_TOKEN = json.getString("token");
                        Log.d(TAG, "Token получен: " + SAVED_TOKEN);
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "JSON Error: " + e.getMessage());
                }
                latchLogin.countDown();
            }
            @Override
            public void onError(String error) {
                Log.e(TAG, "Login FAIL: " + error);
                latchLogin.countDown();
            }
        }).execute();
        latchLogin.await(10, TimeUnit.SECONDS);
        assertNotNull("Токен не получен! Тесты остановлены.", SAVED_TOKEN);
    }

    // --- 2. USER ---
    @Test
    public void test02_User_Update() throws InterruptedException {
        Log.d(TAG, ">>> /api/user/update");
        CountDownLatch latch = new CountDownLatch(1);

        User updateData = new User();
        updateData.email = SAVED_EMAIL;
        updateData.password = COMMON_PASSWORD;
        updateData.firstname = "Updated";
        updateData.lastname = "User";
        updateData.surname = "Testovich";
        updateData.dateOfBirth = "2000-01-01T00:00:00";
        updateData.gender = 0;

        new UserUpdate(SAVED_TOKEN, updateData, getNet(), new MyResponseCallback() {
            @Override
            public void onConpile(String result) {
                Log.d(TAG, "UserUpdate OK: " + result);
                latch.countDown();
            }
            @Override
            public void onError(String error) {
                Log.e(TAG, "UserUpdate FAIL: " + error);
                latch.countDown();
            }
        }).execute();
        latch.await(10, TimeUnit.SECONDS);
    }

    @Test
    public void test03_User_Get() throws InterruptedException {
        Log.d(TAG, ">>> /api/user/get");
        CountDownLatch latch = new CountDownLatch(1);

        new UserGet(SAVED_TOKEN, getNet(), new MyResponseCallback() {
            @Override
            public void onConpile(String result) {
                Log.d(TAG, "UserGet OK: " + result);
                latch.countDown();
            }
            @Override
            public void onError(String error) {
                Log.e(TAG, "UserGet FAIL: " + error);
                latch.countDown();
            }
        }).execute();
        assertTrue(latch.await(10, TimeUnit.SECONDS));
    }

    // --- 3. STOCK (StockGet) ---
    @Test
    public void test04_Stock_Get() throws InterruptedException {
        Log.d(TAG, ">>> /api/stock/get");
        CountDownLatch latch = new CountDownLatch(1);

        new StockGet(SAVED_TOKEN, getNet(), new MyResponseCallback() {
            @Override
            public void onConpile(String result) {
                Log.d(TAG, "StockGet OK: " + result);
                latch.countDown();
            }
            @Override
            public void onError(String error) {
                Log.e(TAG, "StockGet FAIL: " + error);
                latch.countDown();
            }
        }).execute();
        latch.await(10, TimeUnit.SECONDS);
    }

    // --- 4. PROJECT (ProjectCreate) ---
    @Test
    public void test05_Project_Create() throws InterruptedException {
        Log.d(TAG, ">>> /api/project/create");
        CountDownLatch latch = new CountDownLatch(1);

        Project project = new Project();
        project.idType = 1;
        project.name = "AutoTest Project";
        project.description = "Test Description";
        project.startDate = "2024-01-01T00:00:00";
        project.endDate = "2024-12-31T00:00:00";
        project.idCategory = 1;

        new ProjectCreate(SAVED_TOKEN, project, getNet(), new MyResponseCallback() {
            @Override
            public void onConpile(String result) {
                Log.d(TAG, "ProjectCreate OK: " + result);
                latch.countDown();
            }
            @Override
            public void onError(String error) {
                Log.e(TAG, "ProjectCreate FAIL: " + error);
                latch.countDown();
            }
        }).execute();
        latch.await(10, TimeUnit.SECONDS);
    }

    @Test
    public void test06_Project_Get() throws InterruptedException {
        Log.d(TAG, ">>> /api/project/get");
        CountDownLatch latch = new CountDownLatch(1);

        new ProjectGet(SAVED_TOKEN, getNet(), new MyResponseCallback() {
            @Override
            public void onConpile(String result) {
                Log.d(TAG, "ProjectGet OK: " + result);
                latch.countDown();
            }
            @Override
            public void onError(String error) {
                Log.e(TAG, "ProjectGet FAIL: " + error);
                latch.countDown();
            }
        }).execute();
        latch.await(10, TimeUnit.SECONDS);
    }

    // --- 5. PRODUCT (GetId) ---
    @Test
    public void test07_Product_Get() throws InterruptedException {
        Log.d(TAG, ">>> /api/product/get");
        CountDownLatch latch = new CountDownLatch(1);

        new ProductGet(SAVED_TOKEN, getNet(), new MyResponseCallback() {
            @Override
            public void onConpile(String result) {
                Log.d(TAG, "ProductGet OK: " + result);
                latch.countDown();
            }
            @Override
            public void onError(String error) {
                Log.e(TAG, "ProductGet FAIL: " + error);
                latch.countDown();
            }
        }).execute();
        latch.await(10, TimeUnit.SECONDS);
    }

    @Test
    public void test08_Product_Search() throws InterruptedException {
        Log.d(TAG, ">>> /api/product/search");
        CountDownLatch latch = new CountDownLatch(1);

        new ProductSearch("test", SAVED_TOKEN, getNet(), new MyResponseCallback() {
            @Override
            public void onConpile(String result) {
                Log.d(TAG, "ProductSearch OK: " + result);
                latch.countDown();
            }
            @Override
            public void onError(String error) {
                Log.e(TAG, "ProductSearch FAIL: " + error);
                latch.countDown();
            }
        }).execute();
        latch.await(10, TimeUnit.SECONDS);
    }

    @Test
    public void test10_Product_GetId() throws InterruptedException {
        Log.d(TAG, ">>> /api/product/{id}");
        CountDownLatch latch = new CountDownLatch(1);
        // Предполагаем, что товар с ID=1 существует. Если нет, тест может упасть.
        Integer productId = 1;

        new ProductGetId(productId, SAVED_TOKEN, getNet(), new MyResponseCallback() {
            @Override
            public void onConpile(String result) {
                Log.d(TAG, "ProductGetId OK: " + result);
                latch.countDown();
            }
            @Override
            public void onError(String error) {
                Log.e(TAG, "ProductGetId FAIL: " + error);
                latch.countDown();
            }
        }).execute();
        latch.await(10, TimeUnit.SECONDS);
    }

    // --- 6. BASKET (Update & Delete) ---
    @Test
    public void test09_Basket_Create() throws InterruptedException {
        Log.d(TAG, ">>> /api/basket/create");
        CountDownLatch latch = new CountDownLatch(1);
        Integer productId = 1;

        new BasketCreate(productId, SAVED_TOKEN, getNet(), new MyResponseCallback() {
            @Override
            public void onConpile(String result) {
                Log.d(TAG, "BasketCreate OK: " + result);
                latch.countDown();
            }
            @Override
            public void onError(String error) {
                Log.e(TAG, "BasketCreate FAIL: " + error);
                latch.countDown();
            }
        }).execute();
        latch.await(10, TimeUnit.SECONDS);
    }

    @Test
    public void test11_Basket_Get() throws InterruptedException {
        Log.d(TAG, ">>> /api/basket/get");
        CountDownLatch latch = new CountDownLatch(1);

        new BasketGet(SAVED_TOKEN, getNet(), new MyResponseCallback() {
            @Override
            public void onConpile(String result) {
                Log.d(TAG, "BasketGet OK: " + result);
                latch.countDown();
            }
            @Override
            public void onError(String error) {
                Log.e(TAG, "BasketGet FAIL: " + error);
                latch.countDown();
            }
        }).execute();
        latch.await(10, TimeUnit.SECONDS);
    }

    @Test
    public void test12_Basket_Update() throws InterruptedException {
        Log.d(TAG, ">>> /api/basket/update");
        CountDownLatch latch = new CountDownLatch(1);
        Integer productId = 1;
        Integer count = 5;

        new BasketUpdate(productId, count, SAVED_TOKEN, getNet(), new MyResponseCallback() {
            @Override
            public void onConpile(String result) {
                Log.d(TAG, "BasketUpdate OK: " + result);
                latch.countDown();
            }
            @Override
            public void onError(String error) {
                Log.e(TAG, "BasketUpdate FAIL: " + error);
                latch.countDown();
            }
        }).execute();
        latch.await(10, TimeUnit.SECONDS);
    }

    @Test
    public void test13_Order_Order() throws InterruptedException {
        Log.d(TAG, ">>> /api/order/order");
        CountDownLatch latch = new CountDownLatch(1);

        new OrderOrder(SAVED_TOKEN, getNet(), new MyResponseCallback() {
            @Override
            public void onConpile(String result) {
                Log.d(TAG, "OrderOrder OK: " + result);
                latch.countDown();
            }
            @Override
            public void onError(String error) {
                Log.e(TAG, "OrderOrder FAIL: " + error);
                latch.countDown();
            }
        }).execute();
        latch.await(10, TimeUnit.SECONDS);
    }

    @Test
    public void test14_Basket_Delete() throws InterruptedException {
        Log.d(TAG, ">>> /api/basket/delete");
        CountDownLatch latch = new CountDownLatch(1);

        new BasketDelete(SAVED_TOKEN, getNet(), new MyResponseCallback() {
            @Override
            public void onConpile(String result) {
                Log.d(TAG, "BasketDelete OK: " + result);
                latch.countDown();
            }
            @Override
            public void onError(String error) {
                Log.e(TAG, "BasketDelete FAIL: " + error);
                latch.countDown();
            }
        }).execute();
        latch.await(10, TimeUnit.SECONDS);
    }

    // --- 7. LOGOUT ---
    @Test
    public void test99_User_Logout() throws InterruptedException {
        Log.d(TAG, ">>> /api/user/logout");
        CountDownLatch latch = new CountDownLatch(1);

        new UserLogout(SAVED_TOKEN, getNet(), new MyResponseCallback() {
            @Override
            public void onConpile(String result) {
                Log.d(TAG, "Logout OK: " + result);
                latch.countDown();
            }
            @Override
            public void onError(String error) {
                Log.e(TAG, "Logout FAIL: " + error);
                latch.countDown();
            }
        }).execute();
        latch.await(10, TimeUnit.SECONDS);
    }
}