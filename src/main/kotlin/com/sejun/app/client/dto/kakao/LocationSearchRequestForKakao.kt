package com.sejun.app.client.dto.kakao

import com.sejun.app.client.dto.LocationSearchRequest
import lombok.Getter

@Getter
class LocationSearchRequestForKakao : LocationSearchRequest {
    val query: String

    constructor(query: String) {
        this.query = query
    }
}