package com.example.sony.newsapi.screen.article

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast

import com.example.sony.newsapi.R
import com.example.sony.newsapi.repository.RepositoryImpl

class ArticleActivity : AppCompatActivity(), ArticleContract.View {

    private lateinit var presenter: ArticleContract.Presenter
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    private val webViewClient: WebViewClient
        get() = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                presenter.onPageStarted()
            }

            override fun onPageFinished(view: WebView, url: String) {
                presenter.onPageFinished()
            }

            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError
            ) {
                presenter.onReceivedError()
            }
        }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        webView = findViewById(R.id.wb_article)
        progressBar = findViewById(R.id.pb_article)

        val index = intent.getIntExtra(KEY_INDEX, -1)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = webViewClient
        presenter = ArticlePresenter(this, RepositoryImpl())
        presenter.loadUrl(index)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showTitleOfActionBar(title: String) {
        supportActionBar?.title = title
    }

    override fun openPage(url: String) {
        webView.loadUrl(url)
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val KEY_INDEX = "news_index"

        fun launch(context: Context, index: Int) {
            val intent = Intent(context, ArticleActivity::class.java)
            intent.putExtra(KEY_INDEX, index)
            context.startActivity(intent)
        }
    }
}
