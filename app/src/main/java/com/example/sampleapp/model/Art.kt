package com.example.sampleapp.model

data class Art (
    var day: Day
)

data class Day(
    var internacional: List<Artist>
)

data class ArtistResponse(
    var art: List<Artist>
)