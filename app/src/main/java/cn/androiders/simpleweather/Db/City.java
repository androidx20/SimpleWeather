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
    private String name;

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

}
