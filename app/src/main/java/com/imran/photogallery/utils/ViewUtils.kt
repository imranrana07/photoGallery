package com.imran.photogallery.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

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