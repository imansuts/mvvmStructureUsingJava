package com.admin.mvvmwithoutdagger.ui.main;

import android.view.View;

import com.admin.mvvmwithoutdagger.ui.base.BaseViewModel;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    public void onViewClick(View view){
        getNavigator().onClick();
    }
}
