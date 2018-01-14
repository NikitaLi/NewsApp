package com.example.sony.newsapi.screen.newslist;

import com.example.sony.newsapi.Config;
import com.example.sony.newsapi.repository.Repository;
import com.example.sony.newsapi.repository.RepositoryImpl;
import com.example.sony.newsapi.model.Article;
import com.example.sony.newsapi.model.ArticlesResponse;
import com.example.sony.newsapi.networking.NewsAPI;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

class NewsListPresenter implements NewsListContract.Presenter {

    private final NewsListContract.View view;
    private Repository repository;

    NewsListPresenter(NewsListContract.View newsListView) {
        this.view = newsListView;
        this.repository = new RepositoryImpl();
    }

    @Override
    public void loadNewsList() {
        List<Article> articles = repository.loadFromDB();
        view.showNewsList(articles);

        NewsAPI.getApi()
                .getArticles(Config.API_SOURCE, Config.API_NEWS_SORT_BY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ArticlesResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<ArticlesResponse> articlesResponse) {
                        if (articlesResponse != null) {
                            List<Article> newsList = articlesResponse.body().getArticles();
                            repository.saveToDB(newsList);
                            view.hideSwipeRefreshLayout();
                            view.showNewsList(repository.loadFromDB());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideSwipeRefreshLayout();
                        view.showErrorToast();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
