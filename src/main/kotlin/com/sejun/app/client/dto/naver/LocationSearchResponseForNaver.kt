package com.sejun.app.client.dto.naver


class LocationSearchResponseForNaver {
    var lastBuildDate: String
    var total: Int
    var start: Int
    var display: Int
    var items: List<SearchItemByNaver>

    constructor(
        lastBuildDate: String,
        total: Int,
        start: Int,
        display: Int,
        items: List<SearchItemByNaver>
    ) {
        this.lastBuildDate = lastBuildDate
        this.total = total
        this.start = start
        this.display = display
        this.items = items
    }
}