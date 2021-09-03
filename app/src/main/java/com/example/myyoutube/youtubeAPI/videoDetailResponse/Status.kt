package com.example.myyoutube.youtubeAPI.videoDetailResponse


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("embeddable")
    val embeddable: Boolean,
    @SerializedName("license")
    val license: String,
    @SerializedName("madeForKids")
    val madeForKids: Boolean,
    @SerializedName("privacyStatus")
    val privacyStatus: String,
    @SerializedName("publicStatsViewable")
    val publicStatsViewable: Boolean,
    @SerializedName("uploadStatus")
    val uploadStatus: String
)