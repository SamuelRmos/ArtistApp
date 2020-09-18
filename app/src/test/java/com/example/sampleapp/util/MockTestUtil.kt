package com.example.sampleapp.util

import com.example.sampleapp.model.Artist

object MockTestUtil {

    fun mockArtist() = Artist(
            id = 333,
            name = "Pink Floyd",
            poster = "/cvfdffrggv.jpg",
            favorite = false
    )

    fun mockArtistList() = mutableListOf(mockArtist())
}