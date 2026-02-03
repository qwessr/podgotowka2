package com.example.networkmodule.basket;

import com.example.networkmodule.common.CheckInternet;
import com.example.networkmodule.common.MyAsynckTask;
import com.example.networkmodule.common.MyResponseCallback;
import com.example.networkmodule.common.Settings;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BasketUpdate extends MyAsynckTask {
   public Integer idProduct, count;
   public String token;

    public BasketUpdate(Integer idProduct, Integer count, String token, CheckInternet checkInternet, MyResponseCallback callback) {
        super(checkInternet, callback);

        this.idProduct = idProduct;
        this.count = count;
        this.token= token;
    }

    @Override
    protected String doInBackground(Void...voids)
    {
        if(!checkInternet.isWiFiConnection() && !checkInternet.isMobileConnection())
            return  "Error : no internet connection";

        Map<String,String> params = new HashMap<>();
        params.put("idProduct", this.idProduct.toString());
        params.put("count", this.count.toString());

        try {
            Connection.Response response = Jsoup.connect(Settings.Url + "basket/update")
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .method(Connection.Method.PUT)
                    .header("token", token)
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
