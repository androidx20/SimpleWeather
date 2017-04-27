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
    private String countyName;
    private String weatherId;
    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
