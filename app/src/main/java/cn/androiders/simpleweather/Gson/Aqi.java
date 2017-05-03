package cn.androiders.simpleweather.Gson;

/**
 * 胡师寰 创建于 2017-05-02 10:19.
 * E-mail: hxfsc2@gmail.com
 */

public class Aqi {

    public AqiCity city;

    public class AqiCity{
        public String aqi;
        public String pm25;
    }
}
