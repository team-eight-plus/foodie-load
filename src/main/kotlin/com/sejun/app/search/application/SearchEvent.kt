package com.sejun.app.search.application

import com.sejun.app.client.dto.LocationSearchResponse

class SearchEvent(
    val keyword: String,
    val response: LocationSearchResponse) {
}