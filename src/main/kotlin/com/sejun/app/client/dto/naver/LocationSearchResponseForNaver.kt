package com.sejun.app.client.dto.naver

import com.sejun.app.client.dto.LocationSearchResponse
import lombok.Getter

@Getter
class LocationSearchResponseForNaver : LocationSearchResponse {
    var lastBuildDate: String
    var total: Int
    var start: Int
    var display: Int
    var items: List<Any>

    constructor(
        lastBuildDate: String,
        total: Int,
        start: Int,
        display: Int,
        items: List<Any>
    ) {
        this.lastBuildDate = lastBuildDate
        this.total = total
        this.start = start
        this.display = display
        this.items = items
    }
}