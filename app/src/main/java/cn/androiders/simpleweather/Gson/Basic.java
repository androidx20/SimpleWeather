package cn.androiders.simpleweather.Gson;

import com.google.gson.annotations.SerializedName;

/**
 * 胡师寰 创建于 2017-05-02 10:08.
 * E-mail: hxfsc2@gmail.com
 */

public class Basic {

    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{

        @SerializedName("loc")
        public String updateTime;
    }
}
