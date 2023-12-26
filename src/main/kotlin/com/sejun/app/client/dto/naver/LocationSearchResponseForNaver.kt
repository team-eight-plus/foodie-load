package com.sejun.app.client.dto.naver

import com.sejun.app.client.dto.LocationSearchItems

data class LocationSearchResponseForNaver (
    var lastBuildDate: String,
    var total: Int,
    var start: Int,
    var display: Int,
    var items: List<SearchItemByNaver>
) {
    fun toLocationSearchItems(): List<LocationSearchItems> {
        if (items.isEmpty()) {
            return ArrayList<LocationSearchItems>()
        }

        return items.map{ it.toLocationSearchItems()}
    }
}