package com.example.sampleapp.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val name: String,
    val email: String,
    val password: String,
    val birth: String
)