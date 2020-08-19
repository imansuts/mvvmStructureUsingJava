package com.admin.mvvmwithoutdagger.data.datasource.api;

public interface ApiConfig {

    //Timeout
    long READ_TIMEOUT = 5000;
    long CONNECT_TIMEOUT = 4000;
    long WRITE_TIMEOUT = 4000;


//    String API_KEY = "Authorization";
//    String API_KEY_VALUE = "Authorization";
//


    String API_KEY = "";   // insert your api key if have any for your backend
    String API_KEY_VALUE = "";  // insert your api key if have any for your backend
}
