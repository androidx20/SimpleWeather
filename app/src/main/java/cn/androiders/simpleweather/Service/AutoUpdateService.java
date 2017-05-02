package cn.androiders.simpleweather.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import java.io.IOException;

import cn.androiders.simpleweather.Gson.Weather;
import cn.androiders.simpleweather.Utils.HttpUtil;
import cn.androiders.simpleweather.Utils.Utility;
import cn.androiders.simpleweather.Utils.WeatherURL;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AutoUpdateService extends Service {
    private static final String TAG = "AutoUpdateService";
    
    public AutoUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateWeather();
        updateBingPic();

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 8 * 60 * 60 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime,pi);

        return super.onStartCommand(intent, flags, startId);
    }

    private void updateBingPic() {
        String url = WeatherURL.BING_PIC_URL;
        HttpUtil.httpSendRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBodyString = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                editor.putString("bingPic", responseBodyString);
                editor.apply();

               // Log.d(TAG, "onResponse: " + responseBodyString);
            }
        });
    }

    private void updateWeather() {
        SharedPreferences pfs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = pfs.getString("weather",null);
        if(weatherString != null) {
            Weather weather = Utility.handleWeatherResponse(weatherString);
            final String weatherId = weather.basic.weatherId;
            String url= WeatherURL.WEATHER_URL + "&city=" + weatherId;

            HttpUtil.httpSendRequest(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseBodyString = response.body().string();
                    Weather weather = Utility.handleWeatherResponse(responseBodyString);

                    if(weather != null && "ok".equals(weather.status)) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                        editor.putString("weather", responseBodyString);
                        editor.apply();

                       // Log.d(TAG, "onResponse: " + weather.basic.cityName);
                    }

                }
            });
        }
    }
}
