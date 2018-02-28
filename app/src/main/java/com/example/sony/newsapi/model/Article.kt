package com.example.sony.newsapi.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Article : RealmObject() {
    @SerializedName("author")
    var author: String? = null
    @SerializedName("title")
    @PrimaryKey
    var title: String = ""
    @SerializedName("description")
    var description: String = ""
    @SerializedName("url")
    var url: String = ""
    @SerializedName("urlToImage")
    var urlToImage: String? = null
    @SerializedName("publishedAt")
    var publishedAt: String? = null
}
