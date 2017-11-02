package com.example.sony.newsapi.model

import com.google.gson.annotations.SerializedName

class GetArticlesResponse {
    @SerializedName("status")
    var status: String? = null
    @SerializedName("source")
    var source: String? = null
    @SerializedName("sortBy")
    var sortBy: String? = null
    @SerializedName("articles")
    var articles: List<Article>? = null
}
