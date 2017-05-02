package cn.androiders.simpleweather.Gson;

import com.google.gson.annotations.SerializedName;

/**
 * 胡师寰 创建于 2017-05-02 10:42.
 * E-mail: hxfsc2@gmail.com
 */

public class Forecast {
    public String date;

    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;

    public class Temperature{

        public String max;

        public String min;
    }

    public class More{

        @SerializedName("txt_d")
        public String info;
    }

}
