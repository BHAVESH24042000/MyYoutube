package com.example.myyoutube.youtubeAPI.channelDetailsResponse


import com.google.gson.annotations.SerializedName

data class Statistics(
    @SerializedName("hiddenSubscriberCount")
    val hiddenSubscriberCount: Boolean,
    @SerializedName("subscriberCount")
    val subscriberCount: String,
    @SerializedName("videoCount")
    val videoCount: String,
    @SerializedName("viewCount")
    val viewCount: String
)