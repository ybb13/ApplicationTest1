package com.s10.ybb.com.applicationtest1.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2016/2/7.
 */
public class HttpUtil {
    public static void sendHttpRequest(final String address, final HttpCallbackListener listener , final String parms) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
                    if(parms!=""){
                        dos.writeBytes(parms);
                    }
                    Log.d("App1","4");
                    dos.close();
                    Log.d("App1","5");

                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(in,"utf-8"));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    if (listener != null) {
// 回调onFinish()方法
                        Log.d("App1",response.toString());
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
// 回调onError()方法
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


}

