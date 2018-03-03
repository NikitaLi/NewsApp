package com.example.sony.newsapi.networking

import com.example.sony.newsapi.model.ArticlesResponse

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object NewsAPI {
    private const val API_KEY = "0545f526db4148e49a6ca0e4f5973cf5"
    private const val API_PATH = "https://newsapi.org/v2/"

    private val client: OkHttpClient
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
        }

    val api: NewsService
        get() {
            val retrofit = Retrofit.Builder()
                .baseUrl(API_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
            return retrofit.create(NewsService::class.java)
        }

    interface NewsService {
        @GET("top-headlines?apiKey=$API_KEY")
        fun getArticles(@Query("sources") source: String): Observable<Response<ArticlesResponse>>
    }
}
