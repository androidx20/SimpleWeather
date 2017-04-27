package cn.androiders.simpleweather.Utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 胡师寰 创建于 2017-04-27 10:52.
 * E-mail: hxfsc2@gmail.com
 */

public class HttpUtil {
    private static final String TAG = "HttpUtil";


    public static void httpSendRequest(String url, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }

    private static boolean isNetworkAvailable(){

        return true;
    }
}

