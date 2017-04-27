package cn.androiders.simpleweather.Utils;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.androiders.simpleweather.Db.City;
import cn.androiders.simpleweather.Db.Province;

/**
 * 胡师寰 创建于 2017-04-27 11:44.
 * E-mail: hxfsc2@gmail.com
 */

public class Utility {
    private static final String TAG = "Utility";


    public static boolean handleProvinceResponse(String response) {
        if(!TextUtils.isEmpty(response)){
            Log.d(TAG, "handleProvinceResponse: " + response);
            try {
                JSONArray allProvince = new JSONArray(response);
                for(int i = 0, len = allProvince.length(); i<len; i++){
                    JSONObject jsonObject = allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceCode(jsonObject.getInt("id"));
                    province.setProvinceName(jsonObject.getString("name"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }


    public static boolean handleCityResponse(String response, int provinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCity = new JSONArray(response);
                for(int i = 0, len = allCity.length(); i < len; i++){
                    JSONObject cityObject = allCity.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setProvinceId(provinceId);
                    city.save();
                }

                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountryResponse(String response, int cityId){

        return false;
    }
}
