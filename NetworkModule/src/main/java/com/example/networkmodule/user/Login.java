package com.example.networkmodule.user;

import android.os.AsyncTask;

import com.example.networkmodule.common.CheckInternet;
import com.example.networkmodule.common.MyResponseCallback;
import com.example.networkmodule.common.Settings;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Login extends AsyncTask<Void,Void,String> {
    String email,password;
    CheckInternet checkInternet;
    MyResponseCallback callback;

    public Login(String email,String password, CheckInternet checkInternet, MyResponseCallback callback)
    {
        this.email = email;
        this.password = password;
        this.checkInternet = checkInternet;
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void...voids)
    {
        if(!checkInternet.isWiFiConnection() && !checkInternet.isMobileConnection())
            return  "Error : no internet connection";

        Map<String, String> params = new HashMap<>();
        params.put("Email",email);
        params.put("Password",password);

        try {
            Connection.Response response = Jsoup.connect(Settings.Url + "user/login")
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .method(Connection.Method.POST)
                    .data(params)
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

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);

        if(callback != null)
        {
            if (result != null && !result.startsWith("Error"))
                callback.onConpile(result);
            else callback.onError(result);
        }
    }

}
