package com.example.sony.newsapi.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Article : RealmObject() {
    @SerializedName("author")
    var author: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("urlToImage")
    var urlToImage: String? = null
    @SerializedName("publishedAt")
    var publishedAt: String? = null
}
