package com.example.sampleapp.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun AppCompatImageView.loadImage(url: String?) {
    url?.let {
        Glide.with(context)
            .load(it)
            .centerCrop()
            .into(this)
    }
}