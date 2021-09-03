package com.example.myyoutube.youtubeAPI.channelsResponse


import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("channelId")
    val channelId: String,
    @SerializedName("kind")
    val kind: String
)