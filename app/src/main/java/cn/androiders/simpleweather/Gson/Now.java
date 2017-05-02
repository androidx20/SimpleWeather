package cn.androiders.simpleweather.Gson;

import com.google.gson.annotations.SerializedName;

/**
 * 胡师寰 创建于 2017-05-02 10:21.
 * E-mail: hxfsc2@gmail.com
 */

public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More{
        @SerializedName("txt")
        public String info;
    }
}
