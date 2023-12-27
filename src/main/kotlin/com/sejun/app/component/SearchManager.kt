package com.sejun.app.component

import com.sejun.app.client.LocationSearchApiForKakao
import com.sejun.app.client.LocationSearchApiForNaver
import com.sejun.app.client.dto.LocationSearchRequest
import com.sejun.app.client.dto.LocationSearchResponse
import com.sejun.app.client.dto.kakao.LocationSearchRequestForKakao
import com.sejun.app.client.dto.naver.LocationSearchRequestForNaver
import com.sejun.app.config.CircuitBreakerProvider
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class SearchManager(
    val locationSearchApiForNaver: LocationSearchApiForNaver,
    val locationSearchApiForKakao: LocationSearchApiForKakao
)  {

    val log: Logger = LoggerFactory.getLogger(SearchManager::class.java)

    @CircuitBreaker(name = CircuitBreakerProvider.CIRCUIT_SEARCH, fallbackMethod = "requestKakaoLocationSearchApi")
    fun search(request: LocationSearchRequest): LocationSearchResponse {
        return locationSearchApiForNaver.search(LocationSearchRequestForNaver(query = request.query))
    }

    fun requestKakaoLocationSearchApi(request: LocationSearchRequest, e: Throwable):
            LocationSearchResponse {
        log.info("called requestKakaoLocationSearchApi")
        return locationSearchApiForKakao.search(LocationSearchRequestForKakao(query = request.query))
    }
}