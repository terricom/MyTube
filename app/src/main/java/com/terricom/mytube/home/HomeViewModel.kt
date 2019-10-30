package com.terricom.mytube.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.terricom.mytube.data.Submit
import com.terricom.mytube.internet.RetrofitApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getFilm()
    }

    private fun getFilm(){

        coroutineScope.launch {

            var getPropertiesDeferred = RetrofitApi
                .retrofitService.getFilmAsync(Submit())

            try {
                Log.i("Demo", "getPropertiesDeferred = $getPropertiesDeferred")
                val cataloglistResult = getPropertiesDeferred.await()

            } catch (e: Exception) {
                Log.i("Demo", "exception=${e.message}")
            }
        }
    }
}