package com.admin.mvvmwithoutdagger;

import android.app.Application;

import androidx.room.Room;

import com.admin.mvvmwithoutdagger.data.datasource.api.ApiClient;
import com.admin.mvvmwithoutdagger.data.datasource.api.ApiService;
import com.admin.mvvmwithoutdagger.data.datasource.db.AppDataBase;
import com.admin.mvvmwithoutdagger.data.datasource.sharedpref.AppSharedPref;
import com.admin.mvvmwithoutdagger.utils.AppConstants;

public class ApplicationClass extends Application {

    private static ApplicationClass applicationClass;
    private AppDataBase appDataBase;
    private AppSharedPref appSharedPref;


    @Override
    public void onCreate() {
        super.onCreate();
        applicationClass = this;
        appSharedPref = new AppSharedPref(this, AppConstants.SHARED_PREF_NAME);
        appDataBase = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, AppConstants.DB_NAME).build();
    }

    public static synchronized ApplicationClass getInstance(){
        return applicationClass;
    }

    public AppDataBase getAppDatabase() {
        return appDataBase;
    }

    public AppSharedPref getAppSharedPref(){
        return appSharedPref;
    }


    public ApiService getApiServiceWithJacksonFactory() {
        return ApiClient.retrofit(this, getString(R.string.api_base), ApiClient.CONVERTER_TYPE_JACKSON).create(ApiService.class);
    }

    public ApiService getApiServiceWithGsonFactory() {
        return ApiClient.retrofit(this, getString(R.string.api_base), ApiClient.CONVERTER_TYPE_GSON).create(ApiService.class);
    }
}
