package com.aungbophyoe.space.mvisample.util

import android.widget.ImageView
import com.aungbophyoe.space.mvisample.R
import com.squareup.picasso.Picasso

object ImageBindingAdapter {
    fun setImageUrl(imageView: ImageView, url: String) {
        if (url == null) {
            imageView.setImageResource(R.mipmap.ic_launcher);
        } else {
            Picasso.get().load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView)
        }
    }
}