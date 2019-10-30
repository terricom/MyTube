package com.terricom.mytube.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Submit(
    val key: String = "VoiceTube"
) : Parcelable
