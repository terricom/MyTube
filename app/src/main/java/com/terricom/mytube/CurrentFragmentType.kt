package com.terricom.mytube

enum class CurrentFragmentType(val value: String) {
    HOME(MyTubeApp.applicationContext().getString(R.string.title_home)),
    SETTING(MyTubeApp.applicationContext().getString(R.string.title_setting)),
    TIMER(MyTubeApp.applicationContext().getString(R.string.title_timer))
}