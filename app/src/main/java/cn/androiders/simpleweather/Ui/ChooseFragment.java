package cn.androiders.simpleweather.Ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.androiders.simpleweather.R;

/**
 * 胡师寰 创建于 2017-04-27 15:32.
 * E-mail: hxfsc2@gmail.com
 */

public class ChooseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose_area, container,false);

        return view;


    }
}

