package cn.androiders.simpleweather.Db;

import org.litepal.crud.DataSupport;

/**
 * 胡师寰 创建于 2017-04-26 18:26.
 * E-mail: hxfsc2@gmail.com
 */

public class County extends DataSupport {

    /**
     * id : 937
     * name : 苏州
     * weather_id : CN101190401
     */

    private int id;
    private String name;
    private String weather_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(String weather_id) {
        this.weather_id = weather_id;
    }


}
