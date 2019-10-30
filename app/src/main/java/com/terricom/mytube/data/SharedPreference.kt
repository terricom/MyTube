package com.terricom.mytube.data

import android.content.SharedPreferences
import android.util.Log
import com.terricom.mytube.MyTubeApp

private inline fun SharedPreferences.edit(operation:
                                              (SharedPreferences.Editor) -> Unit) {
    val editor = edit()
    operation(editor)
    editor.apply()
}


object UserManager {

    var prefs : SharedPreferences? = MyTubeApp.instance?.getSharedPreferences("token", 0)


    var ifAutoPlay: Boolean? = null
        get() {
            return prefs?.getBoolean("autoPlay", false )
        }
        set(value) {
            field = prefs?.edit()?.putBoolean("autoPlay", value!!)?.commit()
            Log.i("Terri", "UserManager ifAutoPlay = $value")
        }

    var ifSubtitle: Boolean? = null
        get() {
            return prefs?.getBoolean("subtitle", false )
        }
        set(value) {
            field = prefs?.edit()?.putBoolean("subtitle", value!!)?.commit()
        }

    var ifPauseWhen: Boolean? = null
        get() {
            return prefs?.getBoolean("pauseWhen", false )
        }
        set(value) {
            field = prefs?.edit()?.putBoolean("pauseWhen", value!!)?.commit()
        }

    var ifRecommended: Boolean? = null
        get() {
            return prefs?.getBoolean("recommended", false )
        }
        set(value) {
            field = prefs?.edit()?.putBoolean("recommended", value!!)?.commit()
        }

    var ifLearning: Boolean? = null
        get() {
            return prefs?.getBoolean("learning", false )
        }
        set(value) {
            field = prefs?.edit()?.putBoolean("learning", value!!)?.commit()
        }

}