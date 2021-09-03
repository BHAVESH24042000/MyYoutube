package com.example.myyoutube.youtubeAPI

import com.example.myyoutube.Constants
import com.example.myyoutube.youtubeAPI.channelDetailsResponse.channelDetailsResponse
import com.example.myyoutube.youtubeAPI.channelsResponse.channelResult
import com.example.myyoutube.youtubeAPI.videoDetailResponse.videoDetailResponse
import com.example.myyoutube.youtubeAPI.videosResponse.videoResult
import retrofit2.Response

class YoutubeAPIRemoteDataSource {

    private val youtubeApi : YoutubeAPIinterface = YoutubeAPInetworkModule.youtubeApi

    suspend fun getVideosOnQsearch( query : String?) : Response<videoResult?> {
      return youtubeApi.getVideosOnQsearch( "snippet", 20, query, "video", Constants.API_KEY  )
    }

    suspend fun getChannelsOnQsearch(query :String?) : Response<channelResult?>{
        return youtubeApi.getChannelsOnQsearch( "snippet", 20, query, "channel", Constants.API_KEY)
    }

    suspend fun getVideoDetails(id :String?) : Response<videoDetailResponse?>{
        return youtubeApi.getVideoDetails( id, Constants.API_KEY, "statistics,status,contentDetails,snippet")
    }

    suspend fun getChannelDetails(id :String?) : Response<channelDetailsResponse?>{
        return youtubeApi.getChannelDetails( id, Constants.API_KEY, "statistics,status,contentDetails,snippet")
    }


}