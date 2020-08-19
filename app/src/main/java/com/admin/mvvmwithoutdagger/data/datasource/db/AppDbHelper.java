package com.admin.mvvmwithoutdagger.data.datasource.db;

import android.util.Log;

import com.admin.mvvmwithoutdagger.data.model.db.UserDataModel;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class AppDbHelper {

    private final AppDataBase mAppDatabase;

    public AppDbHelper(AppDataBase mAppDatabase) {
        this.mAppDatabase = mAppDatabase;
    }


    public Observable<List<UserDataModel>> getAllUsers() {
        return Observable.fromCallable(new Callable<List<UserDataModel>>() {
            @Override
            public List<UserDataModel> call() throws Exception {
                Log.d("inserted_data_size",": "+mAppDatabase.userDao().loadAll().size());
                return mAppDatabase.userDao().loadAll();
            }
        });
    }

    public Observable<Boolean> insertUser(final UserDataModel user) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Log.d("check_inserted_data",": "+user.name);
                mAppDatabase.userDao().insert(user);
                return true;
            }
        });
    }
}
