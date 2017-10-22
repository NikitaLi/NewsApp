package com.example.sony.newsapi.screen.newslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.sony.newsapi.Config;
import com.example.sony.newsapi.NewsAdapter;
import com.example.sony.newsapi.R;
import com.example.sony.newsapi.model.GetArticlesResponse;

public class NewsListActivity extends AppCompatActivity implements NewsListView {

    public RecyclerView rvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNews = findViewById(R.id.activity_main_recyclerview);
        rvNews.setLayoutManager(new LinearLayoutManager(this));

        showTitle();

        NewsListPresenter presenter = new NewsListPresenter(this);
        presenter.loadNewsList();
    }

    @Override
    public void showTitle() {
        setTitle(Config.SOURCE_NAME);
    }

    @Override
    public void showNewsList(GetArticlesResponse articlesResponse) {
        NewsAdapter newsAdapter = new NewsAdapter(articlesResponse.getArticles());
        rvNews.setAdapter(newsAdapter);
    }

    @Override
    public void showErrorToast() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}