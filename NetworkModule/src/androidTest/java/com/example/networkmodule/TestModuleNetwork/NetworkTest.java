package com.example.networkmodule.TestModuleNetwork;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.networkmodule.common.CheckInternet;
import com.example.networkmodule.common.MyResponseCallback;
import com.example.networkmodule.user.Login;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class NetworkTest {


    private static final String TAG = "NetworkTest";

    @Test
    public void testLoginConnection() throws InterruptedException {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        CheckInternet checkInternet = new CheckInternet(appContext);

        CountDownLatch latch = new CountDownLatch(1);

        Login loginTask = new Login("test@email.com", "password123", checkInternet, new MyResponseCallback() {
            @Override
            public void onConpile(String response) {
                Log.d(TAG, "Успех (ответ сервера): " + response);
                assertNotNull("Ответ не должен быть пустым", response);
                latch.countDown();
            }

            @Override
            public void onError(String error) {
                Log.e(TAG, "Ошибка соединения: " + error);
                latch.countDown();
            }
        });

        Log.d(TAG, "Запуск теста Login...");
        loginTask.execute();

        boolean receivedResponse = latch.await(10, TimeUnit.SECONDS);
        assertTrue("Тайм-аут: Сервер не ответил за 10 секунд", receivedResponse);
    }
}