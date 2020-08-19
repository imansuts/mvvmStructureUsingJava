package com.admin.mvvmwithoutdagger.ui.main.fragment1;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.admin.mvvmwithoutdagger.data.model.api.getspecilitylist.GetSpecilityListResponse;
import com.admin.mvvmwithoutdagger.data.model.db.UserDataModel;
import com.admin.mvvmwithoutdagger.ui.base.BaseViewModel;
import com.admin.mvvmwithoutdagger.ui.main.fragment1.adapter.ListItemsViewModel;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.operators.observable.ObservableSingleSingle;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FragmentOneViewModel extends BaseViewModel<FragmentNavigator> {

    private static final String TAG = "FragmentOneViewModel";
    private final MutableLiveData<List<ListItemsViewModel>> openSourceItemsLiveData = new MutableLiveData<>();
    // Below two functions are for testing purpose
    public void getUserData(){

        getCompositeDisposable().add(fromObservable(getAppDataBase().getAllUsers())
                .map(userDataModels -> userDataModels)
                .flatMap(this::getViewModelList)
                .subscribeOn(get_scheduler_io())
                .observeOn(get_scheduler_ui())
                .subscribe((Consumer<List<ListItemsViewModel>>) userDataModels -> {
                    Log.d("check_db_size", ": " + userDataModels.size());
                    openSourceItemsLiveData.setValue(userDataModels);
                }, (Consumer<Throwable>) throwable -> {
                    Log.d(TAG, "getUserData_throwable: "+throwable.getMessage());
                }));
    }
    public void saveUserData(UserDataModel userDataModel){
        getCompositeDisposable().add(getAppDataBase().insertUser(userDataModel).
                subscribeOn(get_scheduler_io()).
                observeOn(get_scheduler_ui()).
                subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean userDataModels) throws Exception {
                        Log.d("check_result_db", ": " + userDataModels);
                        FragmentOneViewModel.this.getUserData();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("check_result_db_err", ": " + throwable.getMessage());
                        FragmentOneViewModel.this.getUserData();
                    }
                }));
    }


    public void fetchUsersList() {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), "");
        Disposable disposable = getApiServiceWithGsonFactory().getspecilitylist(body)
                .subscribeOn(get_scheduler_io())
                .observeOn(get_scheduler_ui())
                .subscribe(new Consumer<GetSpecilityListResponse>() {
                    @Override
                    public void accept(GetSpecilityListResponse response) throws Exception {
                        if (response!=null) {
                            // Store last login time
                            Log.d("check_response",": "+new Gson().toJson(response));
                        }else {
                            Log.d("check_response",": null response");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("check_response_error",": "+throwable.getMessage());
                    }
                });

        getCompositeDisposable().add(disposable);
    }

    public LiveData<List<ListItemsViewModel>> getListData() {
        return openSourceItemsLiveData;
    }

    private Single<List<ListItemsViewModel>> getViewModelList(List<UserDataModel> repoList) {
        return Observable.fromIterable(repoList)
                .map(new Function<UserDataModel, ListItemsViewModel>() {
                    @Override
                    public ListItemsViewModel apply(UserDataModel repo) throws Exception {
                        return new ListItemsViewModel(
                                repo.name);
                    }
                }).toList();
    }

    @CheckReturnValue
    @NonNull
    @SchedulerSupport(SchedulerSupport.NONE)
    public static <T> Single<T> fromObservable(ObservableSource<? extends T> observableSource) {
        ObjectHelper.requireNonNull(observableSource, "observableSource is null");
        return RxJavaPlugins.onAssembly(new ObservableSingleSingle<T>(observableSource, null));
    }
}
