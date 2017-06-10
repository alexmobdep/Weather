package com.example.alex.largo.weather.ui.change_coordinates.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.alex.largo.weather.R;
import com.example.alex.largo.weather.ui.change_coordinates.OnDataPass;

public class PickCityFragment extends Fragment implements View.OnClickListener {

    private OnDataPass mDataPasser;

    private EditText mCityNameEditText;
    private Button mOkBtn;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mDataPasser = (OnDataPass) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pick_city, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        mCityNameEditText = (EditText) view.findViewById(R.id.city_name_ed);
        mOkBtn = (Button) view.findViewById(R.id.ok_btn);
        mOkBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String cityName = mCityNameEditText.getText().toString();
        mDataPasser.passData(cityName);
    }
}
