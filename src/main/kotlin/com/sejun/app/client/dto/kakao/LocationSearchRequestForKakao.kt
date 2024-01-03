package com.sejun.app.client.dto.kakao

import com.sejun.app.client.dto.LocationSearchRequest
import com.sejun.app.exception.CustomErrorStatus
import com.sejun.app.exception.CustomException

data class LocationSearchRequestForKakao (
    val query: String
) {
    fun validate() {
        if (query.isEmpty()) throw CustomException(CustomErrorStatus.NO_KEYWORD)
    }

    companion object {
        fun convertByLocationSearchRequest(target: LocationSearchRequest): LocationSearchRequestForKakao {
            return LocationSearchRequestForKakao(query = target.query)
        }
    }
}