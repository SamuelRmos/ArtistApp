package com.example.sampleapp.extensions

import android.view.View
import android.widget.ImageView

fun ImageView.hide(){
    visibility = View.GONE
}

fun ImageView.show(){
    visibility = View.VISIBLE
}