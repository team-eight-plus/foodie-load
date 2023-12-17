package com.sejun.app.search.application

import com.sejun.app.client.LocationSearchApiForKakao
import com.sejun.app.client.LocationSearchApiForNaver
import com.sejun.app.client.dto.*
import com.sejun.app.client.dto.naver.LocationSearchResponseForNaver
import com.sejun.app.client.dto.naver.SearchItemByNaver
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class SearchService(
    val locationSearchApiForNaver: LocationSearchApiForNaver,
    val locationSearchApiForKakao: LocationSearchApiForKakao
) {

    fun search(request: LocationSearchRequest): List<Any> {
        // naver API 요청
        val searchResponse = requestNaverLocationSearchApi(request)
        return ArrayList<Any>()
    }

    fun requestNaverLocationSearchApi(request: LocationSearchRequest):
            ResponseEntity<LocationSearchResponse> {
        return locationSearchApiForNaver.search(request)
    }

    fun requestKakaoLocationSearchApi(request: LocationSearchRequest):
            ResponseEntity<LocationSearchResponse> {
        return locationSearchApiForKakao.search(request)
    }
}