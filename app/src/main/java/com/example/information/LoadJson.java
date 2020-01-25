package com.example.information;


import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;


public class LoadJson {
    public static final String LINK = "http://192.168.1.4/information/login.php";

    public void sendDataToServer(int method, HashMap<String, String> hashMap) {
        try {
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();

            //put data to server
            params.put(Var.KEY_METHOD, method);

            if (hashMap != null) {
                for (String key : hashMap.keySet()) {
                    params.put(key, hashMap.get(key));
                }
            }
            System.out.println("Đang gửi...");
            System.out.println(params);

            client.post(LINK, params, new AsyncHttpResponseHandler() {


                @SuppressWarnings("deprecation")
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                    String json = new String(responseBody);
                    System.out.println("onSuccess " + json);
                    onFinishLoadJSonListener.finishLoadJSon(null, json);
                }


                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    String e;
                    if (statusCode == 404) {
                        e = "Del có cái res này đâu ô ơi...";
                    } else if (statusCode == 500) {
                        e = "Có gì đó sai sai ở server cmnr ý!";
                    } else {
                        e = "Hình như không có mạng á...?";
                    }
                    Log.d("onFailure:", e, error);
                    onFinishLoadJSonListener.finishLoadJSon(e, null);
                }
            });

        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }
    }


    public interface OnFinishLoadJSonListener {
        void finishLoadJSon(String error, String json);
    }

    public OnFinishLoadJSonListener onFinishLoadJSonListener;

    public void setOnFinishLoadJSonListener(OnFinishLoadJSonListener onFinishLoadJSonListener) {
        this.onFinishLoadJSonListener = onFinishLoadJSonListener;
    }
}
