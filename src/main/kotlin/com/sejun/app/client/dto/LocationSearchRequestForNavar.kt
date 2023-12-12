package com.sejun.app.client.dto

data class LocationSearchRequestForNavar (
    val query: String,
    val start: Int,
    val display: Int,
    val sort: String
)