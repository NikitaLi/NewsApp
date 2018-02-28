package com.example.sony.newsapi.model

import com.google.gson.annotations.SerializedName

class ArticlesResponse {
    @SerializedName("status") var status: String = ""
    @SerializedName("source") var source: String = ""
    @SerializedName("sortBy") var sortBy: String = ""
    @SerializedName("articles") var articles: List<Article> = emptyList()
}
