package com.example.myyoutube.youtubeAPI

import com.example.myyoutube.youtubeAPI.channelDetailsResponse.channelDetailsResponse
import com.example.myyoutube.youtubeAPI.channelsResponse.channelResult
import com.example.myyoutube.youtubeAPI.videoDetailResponse.videoDetailResponse
import com.example.myyoutube.youtubeAPI.videosResponse.videoResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeAPIinterface {

// BASE URL:https://www.googleapis.com/youtube/v3/

// https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=20&q=carryminati&type=video&key=

@GET("search")
suspend fun getVideosOnQsearch(
    @Query("part") part:String,
    @Query("maxResults") maxResults:Int,
    @Query("q") q:String?,
    @Query("type") type:String,
    @Query("key") key:String
): Response<videoResult?>

// https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=20&q=carryminati&type=channel&key=

    @GET("search")
    suspend fun getChannelsOnQsearch(
        @Query("part") part:String,
        @Query("maxResults") maxResults:Int,
        @Query("q") q:String?,
        @Query("type") type:String,
        @Query("key") key:String
    ): Response<channelResult?>

// https://www.googleapis.com/youtube/v3/videos?id=d7sewLjzNs0&key=AIzaSyBcBAwmKa45weYZTh1jlWW2nIC6ElWZWG4&part=statistics,status,contentDetails,snippet

    @GET("videos")
    suspend fun getVideoDetails(
    @Query("id") id: String?,
    @Query("key") key:String,
    @Query("part") part:String
    ): Response<videoDetailResponse?>

// https://www.googleapis.com/youtube/v3/channels?id=UCj22tfcQrWG7EMEKS0qLeEg&key=4&part=statistics,status,contentDetails,snippet


    @GET("channels")
    suspend fun getChannelDetails(
        @Query("id") id: String?,
        @Query("key") key:String,
        @Query("part") part:String
    ): Response<channelDetailsResponse?>


}