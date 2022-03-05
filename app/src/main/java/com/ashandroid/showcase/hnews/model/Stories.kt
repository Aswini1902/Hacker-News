package com.ashandroid.showcase.hnews.model

data class Stories(
    val by: String,
    val descendants: Int,
    val id: Int,
    val score: Int,
    val text: String,
    val time: Int,
    val title: String,
    val type: String,
    val url: String)


