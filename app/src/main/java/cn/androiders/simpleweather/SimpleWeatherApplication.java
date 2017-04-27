package cn.androiders.simpleweather;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * 胡师寰 创建于 2017-04-27 10:44.
 * E-mail: hxfsc2@gmail.com
 */

public class SimpleWeatherApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        LitePal.initialize(mContext);


    }

    public static Context getContext(){
        return  mContext;
    }







}
