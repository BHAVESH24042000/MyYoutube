package com.example.myyoutube.viewModels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Looper
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.example.myyoutube.R
import com.example.myyoutube.VideosFragment
import com.example.myyoutube.youtubeAPI.YoutubeAPIRemoteDataSource
import com.example.myyoutube.youtubeAPI.channelsResponse.channelResult
import com.example.myyoutube.youtubeAPI.videosResponse.videoResult
import com.google.android.material.snackbar.Snackbar
import com.google.protobuf.NullValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.logging.Handler

class YoutubeAPIviewModel(application: Application) : AndroidViewModel(application) {


    var _data = MutableLiveData<String>()
    val data: LiveData<String>
        get() = _data

    var _checkdata = MutableLiveData<Int>()
    val checkdata: LiveData<Int>
        get() = _checkdata



    var videosResponse: MutableLiveData<Response<videoResult?>> = MutableLiveData()
    var channelsResponse: MutableLiveData<Response<channelResult?>> = MutableLiveData()

    fun getVideosOnQsearch( query : String?) = viewModelScope.launch(Dispatchers.IO) {
        getVideosOnQsearchSafeCall(query)
    }


   suspend private fun getVideosOnQsearchSafeCall( query : String?) {

       if(hasInternetConnection(getApplication())) {

           try{
                var response  = YoutubeAPIRemoteDataSource().getVideosOnQsearch(query)
               _checkdata.postValue( 1)
                 videosResponse.postValue(response)

           }catch (e:Exception){

               _checkdata.postValue( 2)
           }

       }else{

           _checkdata.postValue( 0)
       }

    }

    fun getChannelsOnQsearch( query : String?) =viewModelScope.launch(Dispatchers.IO) {
        getChannelsOnQsearchSafeCall(query)
    }


    suspend private fun getChannelsOnQsearchSafeCall( query : String?) {

        if(hasInternetConnection(getApplication())) {

            try{
                var response  = YoutubeAPIRemoteDataSource().getChannelsOnQsearch(query)
                channelsResponse.postValue(response)
                _checkdata.postValue( 1)

            }catch (e:Exception){

                _checkdata.postValue( 2)
            }

        }else{

            _checkdata.postValue( 0)
        }

    }


    fun hasInternetConnection(context: Context): Boolean {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
        return false
    }




}