package com.example.networkmodule.user;

import android.adservices.ondevicepersonalization.FederatedComputeScheduler;


import com.example.networkmodule.common.CheckInternet;
import com.example.networkmodule.common.MyAsynckTask;
import com.example.networkmodule.common.MyResponseCallback;
import com.example.networkmodule.common.Settings;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class UserLogin extends MyAsynckTask {

    String email,password;
    public UserLogin(String email, String password, CheckInternet checkInternet, MyResponseCallback callback) {
        super(checkInternet, callback);
        this.email = email;
        this.password = password;
    }

    @Override
    protected String doInBackground(Void...voids)
    {
        if(!checkInternet.isWiFiConnection() && !checkInternet.isMobileConnection())
            return  "Error : no internet connection";

        Map<String, String> Params = new HashMap<>();
        Params.put("email",email);
        Params.put("password",password);

        try {
            Connection.Response response = Jsoup.connect(Settings.Url + "user/login")
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .method(Connection.Method.POST)
                    .data(Params)
                    .execute();

            if (response.statusCode() == 200)
                return response.body();
            else
                return "Error: " + response.body();
        }
        catch (IOException e)
        {
            return "Error: "+e.getMessage();
        }
    }

}
