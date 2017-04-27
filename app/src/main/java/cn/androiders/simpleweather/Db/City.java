package cn.androiders.simpleweather.Db;

import org.litepal.crud.DataSupport;

/**
 * 胡师寰 创建于 2017-04-26 18:25.
 * E-mail: hxfsc2@gmail.com
 */

public class City extends DataSupport {
    /**
     * id : 113
     * name : 南京
     */

    private int id;
    private String cityName;
    private int cityCode;
    private int provinceCode;


    public void setId(int id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public int getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

}
