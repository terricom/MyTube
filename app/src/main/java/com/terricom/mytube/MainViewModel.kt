package com.terricom.mytube

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    // Record current fragment to support data binding
    val currentFragmentType = MutableLiveData<CurrentFragmentType>()

}