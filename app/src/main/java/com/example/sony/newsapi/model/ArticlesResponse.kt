package com.example.sony.newsapi.model

import com.google.gson.annotations.SerializedName

class ArticlesResponse {
    @SerializedName("status") var status: String = ""
    @SerializedName("totalResults") var totalresults: Int = 0
    @SerializedName("articles") var articles: List<Article> = emptyList()
}
