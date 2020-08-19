package com.admin.mvvmwithoutdagger.data.datasource.api;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class ApiClient {

    public static Retrofit mRetrofit;
    private static String credential;
    public static final String CONVERTER_TYPE_JACKSON = "CONVERTER_TYPE_JACKSON";
    public static final String CONVERTER_TYPE_GSON = "CONVERTER_TYPE_GSON";


    public static Retrofit retrofit(final Context context, String apiServerUrl, String converter_type) {

        if (mRetrofit == null && context != null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            //Timeout
            httpClient.readTimeout(ApiConfig.READ_TIMEOUT, TimeUnit.SECONDS);
            httpClient.connectTimeout(ApiConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS);
            httpClient.writeTimeout(ApiConfig.WRITE_TIMEOUT, TimeUnit.SECONDS);

//            //Cache
//            int cacheSize = 10 * 1024 * 1024; // 10 MiB
//            Cache cache = new Cache(context.getCacheDir(), cacheSize);
//            httpClient.cache(cache);

            //Header Interceptor
            httpClient.addInterceptor(new LoggingInterceptor());

            // Authentication Interceptor
            credential = Credentials.basic("admin", "12345");
            AuthenticationInterceptor interceptor_credential =
                    new AuthenticationInterceptor(credential);
            if (!httpClient.interceptors().contains(interceptor_credential)) {
                httpClient.addInterceptor(interceptor_credential);
            }

            OkHttpClient okHttpClient = httpClient.build();
            if (converter_type.equals(CONVERTER_TYPE_JACKSON)) {
                mRetrofit = new Retrofit.Builder()
                        .baseUrl(apiServerUrl)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // For RXAndroid
                        .addConverterFactory(JacksonConverterFactory.create())
                        .client(okHttpClient)
                        .build();
            }else {
                mRetrofit = new Retrofit.Builder()
                        .baseUrl(apiServerUrl)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // For RXAndroid
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();
            }
        }
        return mRetrofit;
    }

    private static class LoggingInterceptor implements Interceptor {

//        .header("Authorization", "asdfghjklLKJHGFDSA")
//        .header("Content-Type", "application/json; charset=utf-8")

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header(ApiConfig.API_KEY, ApiConfig.API_KEY_VALUE)
                    .addHeader("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();
            long t1 = System.nanoTime();
            String requestLog = String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers());
            if (request.method().compareToIgnoreCase("post") == 0) {
                requestLog = "\n" + requestLog + "\n" + bodyToString(request);
            }
            Log.e("CITIZEN_HTTP_REQ_HEADER", "request" + "\n" + requestLog);

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();

            String responseLog = String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers());

            String bodyString = response.body().string();

            Log.e("CITIZEN_HTTP_RESPONSE", "response" + "\n" + responseLog + "\n" + bodyString);

            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), bodyString))
                    .build();
        }
    }

    public static class AuthenticationInterceptor implements Interceptor {

        private String authToken;

        public AuthenticationInterceptor(String token) {
            this.authToken = token;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            Request.Builder builder = original.newBuilder()
                    .header("Authorization", authToken);

            Request request = builder.build();
            return chain.proceed(request);
        }
    }

    private static String bodyToString(final Request request) {

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
