package com.admin.mvvmwithoutdagger.ui.base;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

import com.admin.mvvmwithoutdagger.ApplicationClass;
import com.admin.mvvmwithoutdagger.data.datasource.api.ApiService;
import com.admin.mvvmwithoutdagger.data.datasource.db.AppDbHelper;
import com.admin.mvvmwithoutdagger.data.datasource.sharedpref.AppSharedPref;

import java.lang.ref.WeakReference;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseViewModel<N> extends ViewModel {

    private CompositeDisposable mCompositeDisposable;

    private final ObservableBoolean mIsLoading = new ObservableBoolean();

    private WeakReference<N> mNavigator;

    public BaseViewModel() {
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    protected N getNavigator() {
        return mNavigator.get();
    }

    protected CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    protected void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    protected ApiService getApiServiceWithJacksonFactory() {
        return ApplicationClass.getInstance().getApiServiceWithJacksonFactory();
    }

    protected ApiService getApiServiceWithGsonFactory() {
        return ApplicationClass.getInstance().getApiServiceWithGsonFactory();
    }

    protected AppSharedPref getAppSharedPref(){
        return ApplicationClass.getInstance().getAppSharedPref();
    }

    protected AppDbHelper getAppDataBase(){
        return new AppDbHelper(ApplicationClass.getInstance().getAppDatabase());
    }


    protected Scheduler get_scheduler_computation() {
        return Schedulers.computation();
    }

    protected Scheduler get_scheduler_io() {
        return Schedulers.io();
    }

    public Scheduler get_scheduler_ui() {
        return AndroidSchedulers.mainThread();
    }
}
