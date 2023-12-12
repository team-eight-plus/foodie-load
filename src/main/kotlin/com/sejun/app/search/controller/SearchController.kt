package com.sejun.app.search.controller

import com.sejun.app.client.NaverLocationSearchApi
import com.sejun.app.client.dto.LocationSearchRequestForNavar
import com.sejun.app.client.dto.SearchItemByNaver
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController(
    val naverLocationSearchApi: NaverLocationSearchApi
) {

    /**
     * naver api 테스트 요청
     */
    @GetMapping("/search-test")
    fun test(): List<SearchItemByNaver> {
        //TODO: 실제 요청에 대해서는 service 계층으로 연결 필요
        return naverLocationSearchApi.search(LocationSearchRequestForNavar(
            "coffee", 1, 1, "RANDOM"))
    }
}