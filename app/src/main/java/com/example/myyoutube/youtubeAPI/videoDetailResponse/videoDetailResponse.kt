package com.example.myyoutube.youtubeAPI.videoDetailResponse


import com.google.gson.annotations.SerializedName

data class videoDetailResponse(
    @SerializedName("etag")
    val etag: String,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("pageInfo")
    val pageInfo: PageInfo
)