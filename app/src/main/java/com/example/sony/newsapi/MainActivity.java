package com.example.sony.newsapi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.sony.newsapi.model.GetArticlesResponse;
import com.example.sony.newsapi.networking.NewsAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView newsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(Config.SOURCE_NAME);

        newsRecyclerView = findViewById(R.id.activity_main_recyclerview);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<GetArticlesResponse> call = NewsAPI.getApi().getArticles(
                Config.API_SOURCE,
                Config.API_NEWS_SORT_BY
        );
        call.enqueue(new Callback<GetArticlesResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetArticlesResponse> call, @NonNull Response<GetArticlesResponse> response) {
                GetArticlesResponse articlesResponse = response.body();
                NewsStore.setNewsArticles(articlesResponse.getArticles());
                NewsAdapter newsAdapter = new NewsAdapter(articlesResponse.getArticles());
                newsRecyclerView.setAdapter(newsAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<GetArticlesResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}