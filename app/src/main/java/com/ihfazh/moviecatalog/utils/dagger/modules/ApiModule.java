package com.ihfazh.moviecatalog.utils.dagger.modules;

import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

@Module
public class ApiModule {
    private static final String TAG = "ApiModule";

    @Provides
    @Singleton
    OkHttpClient provideClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    HttpUrl oldUrl = request.url();

                    HttpUrl newUrl = oldUrl.newBuilder()
                            .addQueryParameter("api_key", "5a75ba7d3f64c95f1280e7aa05cd2bc8")
                            .build();


                    Request newRequest = request.newBuilder()
                            .url(newUrl)
                            .build();

                    Log.d(TAG, "provideClient: " + newUrl.toString());

                    return chain.proceed(newRequest);
                });
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiService provideService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}

