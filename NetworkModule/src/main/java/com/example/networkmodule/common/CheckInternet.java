package com.example.networkmodule.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class CheckInternet {
    public ConnectivityManager Manager;

    public CheckInternet(Context context)
    {
        Manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isWiFiConnection(){
        if(Manager ==null) return false;
        NetworkInfo WifiNetwork = Manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return WifiNetwork !=null && WifiNetwork.isConnected();
    }

    public boolean isMobileConnection(){
        if(Manager == null) return false;
        NetworkInfo MobileNetwork = Manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return MobileNetwork != null && MobileNetwork.isConnected();
    }

    public boolean isInternet() {
        return isWiFiConnection() || isMobileConnection();
    }
}
