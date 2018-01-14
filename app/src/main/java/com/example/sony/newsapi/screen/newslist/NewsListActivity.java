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

public class NewsListActivity extends AppCompatActivity implements NewsListContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    public RecyclerView rvNews;
    public SwipeRefreshLayout swipeRefreshLayout;
    public NewsListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showTitle();

        rvNews = findViewById(R.id.rv_news);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rvNews.setLayoutManager(layoutManager);

        presenter = new NewsListPresenter(this);
        presenter.loadNewsList();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
    }

    @Override
    public void showTitle() {
        setTitle(Config.SOURCE_NAME);
    }

    @Override
    public void hideSwipeRefreshLayout() {
        swipeRefreshLayout.setRefreshing(false);
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
        presenter.loadNewsList();
        rvNews.smoothScrollToPosition(rvNews.getAdapter().getItemCount() - 1);
    }
}