package com.sejun.app.client.dto.naver

import com.sejun.app.exception.CustomErrorStatus
import com.sejun.app.exception.CustomException

data class LocationSearchRequestForNaver (
    var query: String = "",
    var start: Int = 1,
    var display: Int = 5,
    var sort: String = "random"
) {
    fun validate() {
        if (query.isEmpty()) throw CustomException(CustomErrorStatus.NO_KEYWORD)
    }
}