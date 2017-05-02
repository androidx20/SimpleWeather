package cn.androiders.simpleweather.Gson;

import com.google.gson.annotations.SerializedName;

/**
 * 胡师寰 创建于 2017-05-02 10:25.
 * E-mail: hxfsc2@gmail.com
 */

public class Suggestion {

    @SerializedName("comf")
    public Comfort comfort;

    @SerializedName("cw")
    public Carwash carwash;

    public Sport sport;

    public class Comfort{
        @SerializedName("txt")
        public String info;
    }

    public class Carwash{
        @SerializedName("txt")
        public String info;
    }

    public class Sport{
        @SerializedName("txt")
        public String info;
    }
}
