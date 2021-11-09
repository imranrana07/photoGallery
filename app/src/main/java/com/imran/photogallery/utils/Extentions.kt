package com.imran.photogallery.utils

import android.app.DownloadManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.lang.Exception
import java.net.URL
import java.util.*

fun Fragment.toast(message:String){
    Toast.makeText(context,message,Toast.LENGTH_LONG).show()
}
fun Context.toast(message:String){
    Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
}

@BindingAdapter("imageUrl", "error")
fun loadImage(view: ImageView, url: String, error: Drawable) {
    Glide.with(view.context).load(url).error(error).into(view)
}

fun URL.toBitmap(): Bitmap?{
    return try {
        BitmapFactory.decodeStream(openStream())
    }catch (e: IOException){
        null
    }
}

fun Fragment.saveImage(imageUrl: String){
    try {
        val manager = requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(imageUrl)
        val request = DownloadManager.Request(uri)
        request.setAllowedNetworkTypes(
            DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE
        ).setAllowedOverRoaming(false)
            .setTitle("Downloading_Image")
            .setMimeType("image/*")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalFilesDir(
                requireContext(), Environment.DIRECTORY_PICTURES,File.separator+"Test.jpeg"
            )
        manager.enqueue(request)
    }catch (e: Exception){

    }
}