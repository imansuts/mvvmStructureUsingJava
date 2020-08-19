package com.admin.mvvmwithoutdagger.data.datasource.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallback<M> implements Callback<M> {

    private static final String TAG = "ApiCallback";

    public abstract void onSuccess(M model);

    public abstract void onFailure(int code, String msg);

    public abstract void onThrowable(Throwable t);

    public abstract void onFinish();

    @Override
    public void onResponse(Call<M> call, Response<M> response) {

        if (response.isSuccessful()) {
            onSuccess(response.body());
            System.out.println("response: " + response.body().toString());
            //System.out.println("response: "+response.body());
        } else {
//            onFailure(response.code(), response.errorBody().toString());
            onFailure(response.code(), response.message());
        }
        onFinish();
    }

    @Override
    public void onFailure(Call<M> call, Throwable t) {
        onThrowable(t);
        onFinish();
    }
}
