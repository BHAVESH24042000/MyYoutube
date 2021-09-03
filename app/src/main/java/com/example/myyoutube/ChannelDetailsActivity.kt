package com.example.myyoutube

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.myyoutube.adapters.getphotoResult
import com.example.myyoutube.youtubeAPI.YoutubeAPIRemoteDataSource
import com.example.myyoutube.youtubeAPI.channelDetailsResponse.channelDetailsResponse
import com.example.myyoutube.youtubeAPI.videoDetailResponse.videoDetailResponse
import kotlinx.android.synthetic.main.activity_channel_details.*
import kotlinx.android.synthetic.main.activity_channel_details.channelTitle
import kotlinx.android.synthetic.main.activity_video_details.*
import kotlinx.android.synthetic.main.activity_video_details.main_imageView
import kotlinx.android.synthetic.main.activity_video_details.summary_textView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ChannelDetailsActivity : AppCompatActivity() {

    var channelID:String? = null
    var response : Response<channelDetailsResponse?>? = null



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel_details)
        getSupportActionBar()?.setTitle("Channel Details")
        channelID = intent.getStringExtra("CHANNELID").toString()
        //Log.i("VideoDetailsACTI" , videoID.toString())

        lifecycleScope.launch(Dispatchers.IO) {
            response = YoutubeAPIRemoteDataSource().getChannelDetails(channelID)
           // Log.i("VideoDetailsInsideLife" , response.toString())

            withContext(Dispatchers.Main) {
                Log.i("VideoDetailsACTI" , response.toString())
                channelTitle.text = response?.body()?.items?.get(0)?.snippet?.title.toString()
                getphotoResult( response?.body()?.items?.get(0)?.snippet?.thumbnails?.high?.url, main_imageView)
                subscriberCount.text = "SubscriberCount :" +response?.body()?.items?.get(0)?.statistics?.subscriberCount.toString()
                videoCount.text = "VideoCount :" + response?.body()?.items?.get(0)?.statistics?.videoCount.toString()
                viewCount.text = "ViewCount :" + response?.body()?.items?.get(0)?.statistics?.viewCount.toString()
                summary_textView.text = "Description :" + response?.body()?.items?.get(0)?.snippet?.description.toString()

            }

        }

    }
}