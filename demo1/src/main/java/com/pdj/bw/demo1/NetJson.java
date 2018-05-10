package com.pdj.bw.demo1;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * author:Created by WangZhiQiang on 2018/5/9.
 */

public class NetJson {
    public static String getNetJson(String urlString) {
        try {
            URL url = new URL(urlString);//专门处理网络请求的一个类，把接口包装成url对象；
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();//获取请求网络的状态码
            if (responseCode == 200) {
                InputStream inputStream = urlConnection.getInputStream();//得到服务器返回的数据流；
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();//可拼接字符串；
                String temp = "";
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp);
                }
                String result = stringBuilder.toString();
                Log.e("wzq--result", result);
                return result;
            } else {
                //do nothing
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("wzq", e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("wzq", e.toString());
        }
        return "";
    }
}
