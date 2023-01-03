package com.example.dogwalking.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public SearchViewModel() {
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