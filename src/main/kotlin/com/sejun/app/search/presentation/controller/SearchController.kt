package com.sejun.app.search.presentation.controller

import com.sejun.app.client.LocationSearchApiForKakao
import com.sejun.app.client.dto.kakao.LocationSearchRequestForKakao
import com.sejun.app.client.dto.naver.LocationSearchRequestForNaver
import com.sejun.app.client.dto.naver.SearchItemByNaver
import com.sejun.app.common.Response
import com.sejun.app.search.application.SearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.ArrayList

@RestController
class SearchController(
    val locationSearchApiForKakao: LocationSearchApiForKakao,
    val searchService: SearchService
) {

    /**
     * naver api 테스트 요청
     */
    @GetMapping("/search")
    fun search(@RequestParam query: String): Response {
        return Response(200, "success",
            searchService.search(LocationSearchRequestForKakao(query = query)))
    }

    /**
     * kakao api 테스트 요청
     */
    @GetMapping("/search-test")
    fun test(@RequestParam query: String): List<Any> {
        val response = locationSearchApiForKakao.search(LocationSearchRequestForKakao(query = query))
        return ArrayList<SearchItemByNaver>()
    }
}