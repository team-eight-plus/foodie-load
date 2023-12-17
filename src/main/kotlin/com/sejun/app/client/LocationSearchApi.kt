package com.sejun.app.client

import com.sejun.app.client.dto.LocationSearchRequest
import com.sejun.app.client.dto.LocationSearchResponse
import org.springframework.http.ResponseEntity

interface LocationSearchApi {
    fun search(request: LocationSearchRequest): ResponseEntity<LocationSearchResponse>
}