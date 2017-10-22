package com.example.sony.newsapi.networking;

import com.example.sony.newsapi.model.GetArticlesResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NewsAPI {
    private static final String API_KEY = "0545f526db4148e49a6ca0e4f5973cf5";
    private static final String API_PATH = "https://newsapi.org/v1/";

    private static NewsService newsService = null;

    private static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = client.newBuilder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addNetworkInterceptor(httpLoggingInterceptor);
        builder.build();
        return client;
    }

    public static NewsService getApi() {
        if (newsService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_PATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build();
            newsService = retrofit.create(NewsService.class);
        }
        return newsService;
    }

    public interface NewsService {
        @GET("articles?apiKey=" + API_KEY)
        Call<GetArticlesResponse> getArticles(@Query("source") String source, @Query("sortBy") String sortBy);
    }
}
