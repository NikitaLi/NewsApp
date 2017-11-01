package com.example.sony.newsapi.screen.article;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sony.newsapi.NewsStore;
import com.example.sony.newsapi.R;

public class ArticleActivity extends AppCompatActivity implements ArticleView {
    private static final String KEY_INDEX = "news_index";

    private ProgressBar progressBar;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        WebView webView = findViewById(R.id.wb_article);
        progressBar = findViewById(R.id.pb_article);

        final ArticlePresenter presenter = new ArticlePresenter(this);
        int index = getIntent().getIntExtra(KEY_INDEX, -1);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                presenter.onPageStarted();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                presenter.onPageFinished();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                presenter.onReceivedError();
            }
        });
        webView.loadUrl(presenter.getArticleUrl(index));
    }

    public static void launch(Context context, int index) {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra(KEY_INDEX, index);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitleOfActionBar(int index) {
        getSupportActionBar().setTitle(NewsStore.getNewsArticles().get(index).getTitle());
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
