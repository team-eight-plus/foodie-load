package com.sejun.app.client.dto.kakao

import com.sejun.app.client.dto.LocationSearchItems

data class LocationSearchResponseForKakao (
    var meta: Any,
    var documents: List<SearchItemByKakao>
) {
    fun toLocationSearchItems(): List<LocationSearchItems> {
        if (documents.isEmpty()) {
            return ArrayList<LocationSearchItems>()
        }

        return documents.map{ it.toLocationSearchItems()}
    }
}