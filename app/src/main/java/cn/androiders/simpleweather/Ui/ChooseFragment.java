package cn.androiders.simpleweather.Ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.androiders.simpleweather.Db.City;
import cn.androiders.simpleweather.Db.County;
import cn.androiders.simpleweather.Db.Province;
import cn.androiders.simpleweather.MainActivity;
import cn.androiders.simpleweather.R;
import cn.androiders.simpleweather.SimpleWeatherApplication;
import cn.androiders.simpleweather.Utils.HttpUtil;
import cn.androiders.simpleweather.Utils.Utility;
import cn.androiders.simpleweather.WeatherActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 胡师寰 创建于 2017-04-27 15:32.
 * E-mail: hxfsc2@gmail.com
 */

public class ChooseFragment extends Fragment {
    private static final String TAG = "ChooseFragment";

    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTRY = 2;


    //省份列表
    List<Province> provinceList;
    //市区列表
    List<City> cityList;
    //县区列表
    List<County> countyList;


    private int currentLevel;


    private Province selectProvince;
    private City selectCity;


    private ProgressDialog progressDialog;

    private ListView listView;
    private TextView titleText;
    private Button backButton;
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<String>();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose_area, container,false);

        titleText = (TextView) view.findViewById(R.id.title_text);
        backButton = (Button) view.findViewById(R.id.back_btn);
        listView = (ListView) view.findViewById(R.id.list_view);

        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(currentLevel == LEVEL_PROVINCE){
                selectProvince = provinceList.get(position);
                queryCities();
            }else if(currentLevel == LEVEL_CITY){
                selectCity = cityList.get(position);
                queryCounties();
            }else if(currentLevel == LEVEL_COUNTRY){

                String weatherId = countyList.get(position).getWeatherId();
                if(getActivity() instanceof MainActivity){
                    Intent intent = new Intent(getActivity(), WeatherActivity.class);
                    intent.putExtra("weatherId", weatherId);
                    startActivity(intent);
                    getActivity().finish();
                }else if(getActivity() instanceof WeatherActivity){
                    WeatherActivity activity = (WeatherActivity) getActivity();
                    activity.drawerLayout.closeDrawers();
                    activity.swipeRefresh.setRefreshing(true);
                    activity.requestWeather(weatherId);
                }
            }
            }


        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentLevel == LEVEL_COUNTRY){
                    queryCities();
                }else if(currentLevel == LEVEL_CITY){
                    queryProvince();
                }
            }
        });

        queryProvince();

    }

    private void queryProvince() {
        titleText.setText("中国");
        backButton.setVisibility(View.GONE);
        provinceList = DataSupport.findAll(Province.class);

        if(provinceList.size() > 0){
            dataList.clear();
            for (Province province : provinceList){
                dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
        }else{
            String url = "http://guolin.tech/api/china";
            queryFromService(url, "province");
        }


    }

    private void queryCities() {
        titleText.setText(selectProvince.getProvinceName());
        backButton.setVisibility(View.VISIBLE);

        cityList = DataSupport.where("provinceid = ?", String.valueOf(selectProvince.getId())).find(City.class);
        if(cityList.size() > 0){
            dataList.clear();
            for (City city : cityList){
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_CITY;
        }else{
            int provinceCode = selectProvince.getProvinceCode();
            String url = "http://guolin.tech/api/china/" + provinceCode;
            queryFromService(url, "city");
        }
    }

    private void queryCounties() {
        titleText.setText(selectCity.getCityName());
        backButton.setVisibility(View.VISIBLE);

        countyList = DataSupport.where("cityid = ?", String.valueOf(selectCity.getCityCode())).find(County.class);
        if(countyList.size() > 0){
            dataList.clear();
            for(County county : countyList){
                dataList.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_COUNTRY;
        }else{
            int provinceId = selectProvince.getProvinceCode();
            int cityId = selectCity.getCityCode();
            String url = "http://guolin.tech/api/china/" + provinceId + "/" + cityId;
            queryFromService(url, "county");
        }

    }


    private void queryFromService(String url, final String type) {
        showDialog();

        HttpUtil.httpSendRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideDialog();
                        Toast.makeText(SimpleWeatherApplication.getContext(), "请求错误", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String textResponse = response.body().string();
                boolean result = false;
                if("province".equals(type)){
                    result = Utility.handleProvinceResponse(textResponse);
                }else if("city".equals(type)){
                    result = Utility.handleCityResponse(textResponse, selectProvince.getProvinceCode());

                }else if("county".equals(type)){
                    result = Utility.handleCountryResponse(textResponse, selectCity.getCityCode());
                }

                if(result){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideDialog();
                            if("province".equals(type)){
                                queryProvince();
                            }else if("city".equals(type)){
                                queryCities();
                            }else if("county".equals(type)){
                                queryCounties();
                            }
                        }
                    });
                }
            }
        });
    }


    private void showDialog(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("正在请求数据...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    private void hideDialog(){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }
}

