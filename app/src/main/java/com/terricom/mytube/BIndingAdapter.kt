package com.terricom.mytube

import android.util.Log
import android.widget.ImageView
import android.widget.Switch
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String) {
    imgUrl?.let {

        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(
                        R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background))
            .into(imgView)
    }
}

@BindingAdapter("switchChecked")
fun bindSwitch(switch: Switch, clicked: Boolean) {

    when(clicked){
        true -> {switch.isChecked = true
        }
        false -> {switch.isChecked = false
        }
    }
}
