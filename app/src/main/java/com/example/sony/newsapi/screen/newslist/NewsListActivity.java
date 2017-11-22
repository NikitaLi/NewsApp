package com.example.sony.newsapi.screen.newslist;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.sony.newsapi.Config;
import com.example.sony.newsapi.R;
import com.example.sony.newsapi.model.Article;

import java.util.List;

public class NewsListActivity extends AppCompatActivity implements NewsListView,
        SwipeRefreshLayout.OnRefreshListener {

    public RecyclerView rvNews;
    public SwipeRefreshLayout swipeRefreshLayout;
    public NewsListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNews = findViewById(R.id.rv_news);
        rvNews.setLayoutManager(new LinearLayoutManager(this));

        showTitle();

        presenter = new NewsListPresenter(this);
        presenter.loadNewsList();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void showTitle() {
        setTitle(Config.SOURCE_NAME);
    }

    @Override
    public void showNewsList(List<Article> newsList) {
        NewsAdapter newsAdapter = new NewsAdapter(newsList);
        rvNews.setAdapter(newsAdapter);
    }

    @Override
    public void showErrorToast() {
        Toast.makeText(this, "Нет сети", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.loadNewsList();
        swipeRefreshLayout.setRefreshing(false);
    }
}