package cn.androiders.simpleweather.Gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 胡师寰 创建于 2017-05-02 10:56.
 * E-mail: hxfsc2@gmail.com
 */

public class Weather {
    public String status;

    public Basic basic;

    public Api api;

    public Now now;

    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecast;

}
