package com.sejun.app.client.dto.naver

import com.sejun.app.client.dto.LocationSearchRequest
import com.sejun.app.client.dto.kakao.LocationSearchRequestForKakao
import com.sejun.app.exception.CustomErrorStatus
import com.sejun.app.exception.CustomException

data class LocationSearchRequestForNaver (
    var query: String = "",
    var start: Int = 1,
    var display: Int = 5,
    var sort: String = "random"
) {
    companion object {
        fun convertByLocationSearchRequest(target: LocationSearchRequest): LocationSearchRequestForNaver {
            return LocationSearchRequestForNaver(query = target.query)
        }
    }
    fun validate() {
        if (query.isEmpty()) throw CustomException(CustomErrorStatus.NO_KEYWORD)
    }
}