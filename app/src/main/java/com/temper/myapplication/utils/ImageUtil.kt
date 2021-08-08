package com.temper.myapplication.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL

object ImageUtil {

    fun getImageUrl(url : String): Bitmap? {
        var imageUrl  = URL(url)
        return BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream())
    }

}