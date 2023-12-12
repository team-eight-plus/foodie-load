package com.sejun.app.client.dto

data class LocationSearchResponseForNavar(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    var items: List<SearchItemByNaver>
)
