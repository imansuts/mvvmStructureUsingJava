package com.admin.mvvmwithoutdagger.data.datasource.api;


import com.admin.mvvmwithoutdagger.data.model.api.getspecilitylist.GetSpecilityListResponse;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

//    @Headers({
//            "x-api-key: 123456"
//    })
    @POST("api/listspeciality")
    Single<GetSpecilityListResponse> getspecilitylist(
            @Body RequestBody requestBody
    );

}
