package com.sejun.app.search.presentation.controller

import com.sejun.app.client.LocationSearchApiForKakao
import com.sejun.app.client.dto.LocationSearchRequest
import com.sejun.app.client.dto.kakao.LocationSearchRequestForKakao
import com.sejun.app.client.dto.naver.SearchItemByNaver
import com.sejun.app.common.Response
import com.sejun.app.search.application.SearchService
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController(
    val locationSearchApiForKakao: LocationSearchApiForKakao,
    val searchService: SearchService,
    val resistry: CircuitBreakerRegistry
) {

    val log: Logger = LoggerFactory.getLogger(SearchController::class.java)

    /**
     * naver api 테스트 요청
     */
    @GetMapping("/search")
    fun search(@RequestParam query: String): Response {
        return Response(200, "success",
            searchService.search(LocationSearchRequest(query = query)))
    }

    /**
     * kakao api 테스트 요청
     */
    @GetMapping("/search-test")
    fun test(@RequestParam query: String): Response {
        return Response(200, "success",locationSearchApiForKakao.search(LocationSearchRequestForKakao(query = query)))
    }
}