package com.sejun.app.client

import com.sejun.app.client.dto.LocationSearchRequest
import com.sejun.app.client.dto.naver.LocationSearchResponseForNaver
import org.springframework.http.ResponseEntity

interface LocationSearchApi {
    fun search(request: LocationSearchRequest): ResponseEntity<LocationSearchResponseForNaver>
}