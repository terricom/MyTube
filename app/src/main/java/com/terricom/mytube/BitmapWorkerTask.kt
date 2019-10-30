package com.terricom.mytube

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import androidx.collection.LruCache
import java.net.URL

class BitmapWorkerTask(private val mImageView: ImageView, private val imgUrl: String) : AsyncTask<Int, Void, Bitmap>() {

    override fun doInBackground(vararg p0: Int?): Bitmap? {

        Log.i("Terri", "getBitmapFromMemCache= ${getBitmapFromMemCache(imgUrl) == null}")
        if (getBitmapFromMemCache(imgUrl) == null){
            val options = BitmapFactory.Options()
            options.inPreferredConfig = Bitmap.Config.ARGB_8888

            val inputStream = URL(imgUrl).openStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()

            addBitmapToMemoryCache(imgUrl, bitmap)
        }

        Log.i("Terri", "Help.cache.getBitmap imgUrl = $imgUrl")
        return getBitmapFromMemCache(imgUrl)

    }

    init {
        initLruCache()
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        if (result != null) {
            mImageView.setImageBitmap(result)

        }
    }

}

private var mMemoryCache: LruCache<String, Bitmap>? = null

private fun initLruCache() {

    val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
    val cacheSize = maxMemory / 2

    mMemoryCache = object : LruCache<String, Bitmap>(cacheSize) {
        override fun sizeOf(key: String, value: Bitmap): Int {
            return value.byteCount / 1024
        }
    }
}


fun addBitmapToMemoryCache(key: String, bitmap: Bitmap) {

    Log.i("Terri", "addBitmapToMemoryCache = ${getBitmapFromMemCache(key) == null}")
    if (getBitmapFromMemCache(key) == null) {
        mMemoryCache?.put(key, bitmap)
    }
}


fun getBitmapFromMemCache(key: String): Bitmap? {
    return mMemoryCache?.get(key)
}