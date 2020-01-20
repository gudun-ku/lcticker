package com.beloushkin.lcticker.extensions

import android.widget.ImageView
import com.beloushkin.lcticker.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadNetworkImage(uri: String?) {
    val options = RequestOptions()
        .placeholder(R.drawable.icon_placeholder)
        .error(R.drawable.icon_placeholder)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}
