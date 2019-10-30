package com.terricom.mytube.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.terricom.mytube.data.UserManager

class SettingViewModel: ViewModel() {

    private val _ifAutoPlay = MutableLiveData<Boolean>()
    val ifAutoPlay: LiveData<Boolean>
        get() = _ifAutoPlay

    private val _ifSubtitle = MutableLiveData<Boolean>()
    val ifSubtitle: LiveData<Boolean>
        get() = _ifSubtitle

    private val _ifPauseWhen = MutableLiveData<Boolean>()
    val ifPauseWhen: LiveData<Boolean>
        get() = _ifPauseWhen

    private val _ifRecommended = MutableLiveData<Boolean>()
    val ifRecommended: LiveData<Boolean>
        get() = _ifRecommended

    private val _ifLearning = MutableLiveData<Boolean>()
    val ifLearning: LiveData<Boolean>
        get() = _ifLearning

    init {
        _ifAutoPlay.value = UserManager.ifAutoPlay
        _ifSubtitle.value = UserManager.ifSubtitle
        _ifPauseWhen.value = UserManager.ifPauseWhen
        _ifRecommended.value = UserManager.ifRecommended
        _ifLearning.value = UserManager.ifLearning
    }

    fun setAutoPlay(currentState: Boolean){
        when(currentState){
            true -> {
                _ifAutoPlay.value = false
                UserManager.ifAutoPlay = false
            }
            false -> {
                _ifAutoPlay.value = true
                UserManager.ifAutoPlay = true
            }
        }
    }

    fun setSubtitle(currentState: Boolean){
        when(currentState){
            true -> {
                _ifSubtitle.value = false
                UserManager.ifSubtitle = false
            }
            false -> {
                _ifSubtitle.value = true
                UserManager.ifSubtitle = true
            }
        }
    }

    fun setPauseWhen(currentState: Boolean){
        when(currentState){
            true -> {
                _ifPauseWhen.value = false
                UserManager.ifPauseWhen = false
            }
            false -> {
                _ifPauseWhen.value = true
                UserManager.ifPauseWhen = true
            }
        }
    }

    fun setRecommended(currentState: Boolean){
        when(currentState){
            true -> {
                _ifRecommended.value = false
                UserManager.ifRecommended = false
            }
            false -> {
                _ifRecommended.value = true
                UserManager.ifRecommended = true
            }
        }
    }

    fun setLearning(currentState: Boolean){
        when(currentState){
            true -> {
                _ifLearning.value = false
                UserManager.ifLearning = false
            }
            false -> {
                _ifLearning.value = true
                UserManager.ifLearning = true
            }
        }
    }
}