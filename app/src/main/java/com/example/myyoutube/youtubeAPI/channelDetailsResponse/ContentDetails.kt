package com.example.myyoutube.youtubeAPI.channelDetailsResponse


import com.google.gson.annotations.SerializedName

data class ContentDetails(
    @SerializedName("relatedPlaylists")
    val relatedPlaylists: RelatedPlaylists
)