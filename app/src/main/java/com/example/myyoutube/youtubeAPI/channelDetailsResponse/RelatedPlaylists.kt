package com.example.myyoutube.youtubeAPI.channelDetailsResponse


import com.google.gson.annotations.SerializedName

data class RelatedPlaylists(
    @SerializedName("likes")
    val likes: String,
    @SerializedName("uploads")
    val uploads: String
)