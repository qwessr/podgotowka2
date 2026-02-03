package com.example.networkmodule.user;

import com.example.networkmodule.common.CheckInternet;
import com.example.networkmodule.common.MyAsynckTask;
import com.example.networkmodule.common.MyResponseCallback;
import com.example.networkmodule.common.Settings;
import com.example.networkmodule.models.User;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserCreate extends MyAsynckTask {
    User user;

    public UserCreate(User user, CheckInternet checkInternet, MyResponseCallback callback) {
        super(checkInternet, callback);

        this.user= user;
    }

    @Override
    protected String doInBackground(Void...voids)
    {
        if(!checkInternet.isWiFiConnection() && !checkInternet.isMobileConnection())
            return  "Error : no internet connection";

        Map<String, String> params = new HashMap<>();
        params.put("Email",user.email);
        params.put("Password",user.password);
        params.put("Firstname",user.firstname);
        params.put("Lastname",user.lastname);
        params.put("Surname",user.surname);
        params.put("DateOfBirth",user.dateOfBirth);
        params.put("Gender",user.gender.toString());

        try {
            Connection.Response response = Jsoup.connect(Settings.Url + "user/create")
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
}
