package com.sejun.app.component

import com.sejun.app.client.LocationSearchApiForKakao
import com.sejun.app.client.LocationSearchApiForNaver
import com.sejun.app.client.dto.LocationSearchRequest
import com.sejun.app.client.dto.LocationSearchResponse
import com.sejun.app.exception.CustomErrorStatus
import com.sejun.app.exception.CustomException
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class SearchManager(
    val locationSearchApiForNaver: LocationSearchApiForNaver,
    val locationSearchApiForKakao: LocationSearchApiForKakao
)  {

    fun search(request: LocationSearchRequest): ResponseEntity<LocationSearchResponse> {
        var searchResponse = requestKakaoLocationSearchApi(request)
        if (searchResponse.statusCode.value() == HttpServletResponse.SC_OK) {
            // naver parser
            return searchResponse
        }

        searchResponse = requestKakaoLocationSearchApi(request)
        if (searchResponse.statusCode.value() == HttpServletResponse.SC_OK) {
            // kakao parser
            return searchResponse
        }

        throw CustomException(CustomErrorStatus.NO_SEARCH)
    }

    @CircuitBreaker(name = "search", fallbackMethod = "requestKakaoLocationSearchApi")
    fun requestNaverLocationSearchApi(request: LocationSearchRequest):
            ResponseEntity<LocationSearchResponse> {
        val response = locationSearchApiForNaver.search(request)

        return locationSearchApiForNaver.search(request)
    }

    fun requestKakaoLocationSearchApi(request: LocationSearchRequest):
            ResponseEntity<LocationSearchResponse> {
        return locationSearchApiForKakao.search(request)
    }
}