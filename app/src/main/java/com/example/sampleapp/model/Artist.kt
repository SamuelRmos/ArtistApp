package com.example.sampleapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Artist")
data class Artist(
    @PrimaryKey
    var id: Int,
    var name: String,
    var poster: String,
    var favorite: Boolean?
) : Parcelable

