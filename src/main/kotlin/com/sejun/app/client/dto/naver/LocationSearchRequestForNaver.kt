package com.sejun.app.client.dto.naver

import com.sejun.app.client.dto.LocationSearchRequest
import lombok.Getter

@Getter
class LocationSearchRequestForNaver : LocationSearchRequest {
    var query: String
    var start: Int
    var display: Int
    var sort: String

    constructor(
        query: String,
        start: Int = 1,
        display: Int = 1,
        sort: String = "RANDOM"
    ) {
        this.query = query
        this.start = start
        this.display = display
        this.sort = sort
    }
}