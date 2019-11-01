package com.terricom.mytube.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.terricom.mytube.data.Submit
import com.terricom.mytube.data.UserManager
import com.terricom.mytube.data.Video
import com.terricom.mytube.internet.RetrofitApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _videoList = MutableLiveData<List<Video>>()
    val videoList : LiveData<List<Video>>
        get() = _videoList

    init {
        getFilm()
    }

    private fun getFilm(){

        coroutineScope.launch {

            var getPropertiesDeferred = RetrofitApi
                .retrofitService.getFilmAsync()

            try {
                val cataloglistResult = getPropertiesDeferred.await()
                _videoList.value = cataloglistResult.videos

                var gsonString = Gson().toJson(cataloglistResult.videos)
                UserManager.videoList = gsonString

            } catch (e: Exception) {
                Log.i("Demo", "exception=${e.message}")
            }
        }
    }
}