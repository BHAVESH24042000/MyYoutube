package com.example.myyoutube

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.myyoutube.adapters.getphotoResult
import com.example.myyoutube.youtubeAPI.YoutubeAPIRemoteDataSource
import com.example.myyoutube.youtubeAPI.videoDetailResponse.videoDetailResponse
import kotlinx.android.synthetic.main.activity_video_details.*

import kotlinx.android.synthetic.main.videos_channels_row_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class VideoDetailsActivity : AppCompatActivity() {

    var videoID:String? = null
    var response : Response<videoDetailResponse?>? = null


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_details)
        getSupportActionBar()?.setTitle("Video Details")

        videoID = intent.getStringExtra("VIDEOID").toString()
        Log.i("VideoDetailsACTI" , videoID.toString())

        lifecycleScope.launch(Dispatchers.IO) {
            response = YoutubeAPIRemoteDataSource().getVideoDetails(videoID)
            Log.i("VideoDetailsInsideLife" , response.toString())

            withContext(Dispatchers.Main) {
                Log.i("VideoDetailsACTI" , response.toString())
                videoTitle.text =  response?.body()?.items?.get(0)?.snippet?.title.toString()
                getphotoResult( response?.body()?.items?.get(0)?.snippet?.thumbnails?.high?.url, main_imageView)
                videoDuration.text = "Duration :" + response?.body()?.items?.get(0)?.contentDetails?.duration.toString()
                videoDefintion.text = "Definition :" + response?.body()?.items?.get(0)?.contentDetails?.definition.toString()
                videoViewCount.text = "ViewCount :" +response?.body()?.items?.get(0)?.statistics?.viewCount.toString()
                videoLikeCount.text =  "LikeCount :" + response?.body()?.items?.get(0)?.statistics?.likeCount.toString()
                videoDisLikeCount.text = "DisLikeCount :" +  response?.body()?.items?.get(0)?.statistics?.dislikeCount.toString()
                videocommentCount.text = "CommentCount :" + response?.body()?.items?.get(0)?.statistics?.commentCount.toString()
                summary_textView.text = "Description :" + response?.body()?.items?.get(0)?.snippet?.description.toString()

            }

        }

    }
}