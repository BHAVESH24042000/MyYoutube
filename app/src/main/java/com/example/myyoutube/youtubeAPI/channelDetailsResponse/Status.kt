package com.example.myyoutube.youtubeAPI.channelDetailsResponse


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("isLinked")
    val isLinked: Boolean,
    @SerializedName("longUploadsStatus")
    val longUploadsStatus: String,
    @SerializedName("privacyStatus")
    val privacyStatus: String
)