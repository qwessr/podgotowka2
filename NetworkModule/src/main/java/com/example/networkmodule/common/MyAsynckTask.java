package com.example.networkmodule.common;

import android.os.AsyncTask;



public class MyAsynckTask extends AsyncTask<Void,Void,String>{

    public MyResponseCallback callback;
    public CheckInternet checkInternet;
    public MyAsynckTask(CheckInternet checkInternet,MyResponseCallback callback )
    {
        this.checkInternet = checkInternet;
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... voids){return "";}

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);

        if (callback != null)
        {
            if (result !=null && !result.startsWith("Error"))
            callback.onConpile(result);
            else callback.onError(result);
        }
    }
}
