package com.terricom.mytube.data

import com.squareup.moshi.Json

data class TestResponse (
    @Json(name = "status") val status: String,
    @Json(name = "videos") val videos: List<Video>
)

data class Video (
    @Json(name = "title") val title: String,
    @Json(name = "img") val img: String
)

data class Videos (
    @Json(name = "videos") val videos: List<Video>
)