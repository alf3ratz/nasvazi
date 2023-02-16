package com.ru.alferatz.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter


//@BindingAdapter("app:imageUrl")
//fun setImageUrl(imageView: ImageView, URL: String) {
//    try {
//        Picasso.get().load(URL).noFade().into(imageView, object : Callback {
//            override fun onSuccess() {
//                imageView.animate().setDuration(300).alpha(1f).start()
//            }
//
//            override fun onError(e: Exception) {
//            }
//        })
//    } catch (e: Exception) {
//    }
//}
//
//@BindingAdapter("android:src")
//fun setIcon(view: ImageView, drawable: Int) {
//    view.setImageResource(drawable)
//}