package com.example.sony.newsapi.screen.newslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.example.sony.newsapi.R
import com.example.sony.newsapi.model.Article
import com.example.sony.newsapi.screen.article.ArticleActivity
import com.example.sony.newsapi.utils.DateUtils

class NewsAdapter internal constructor(private val newsArticles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsArticle = newsArticles[position]
        holder.tvCardTitle.text = newsArticle.title
        holder.tvCardTime.text = DateUtils.formatNewsPublishedDate(newsArticle.publishedAt!!)
        holder.tvCardContent.text = newsArticle.description
        Glide.with(holder.ivCardImage.context)
            .load(newsArticle.urlToImage)
            .into(holder.ivCardImage)
        holder.itemView.setOnClickListener { view ->
            ArticleActivity.launch(
                view.context,
                holder.adapterPosition
            )
        }
    }

    override fun getItemCount(): Int {
        return newsArticles.size
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivCardImage: ImageView = itemView.findViewById(R.id.iv_card_news_image)
        var tvCardTitle: TextView = itemView.findViewById(R.id.tv_card_news_title)
        var tvCardTime: TextView = itemView.findViewById(R.id.tv_card_news_time)
        var tvCardContent: TextView = itemView.findViewById(R.id.tv_card_news_content)
    }
}
