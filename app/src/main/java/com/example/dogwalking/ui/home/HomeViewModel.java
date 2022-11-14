package com.example.dogwalking.ui.home;

import static com.example.dogwalking.R.id.dateText;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
    }

    public void setText(String date){
        mText = new MutableLiveData<>();
        mText.setValue(date);
    }


    public LiveData<String> getText() {
        return mText;
    }
}