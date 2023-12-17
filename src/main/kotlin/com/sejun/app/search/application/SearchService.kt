package com.sejun.app.search.application

import com.sejun.app.client.LocationSearchApiForKakao
import com.sejun.app.client.LocationSearchApiForNaver
import com.sejun.app.client.dto.*
import com.sejun.app.client.dto.naver.LocationSearchResponseForNaver
import com.sejun.app.client.dto.naver.SearchItemByNaver
import com.sejun.app.exception.CustomErrorStatus
import com.sejun.app.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class SearchService(
    val locationSearchApiForNaver: LocationSearchApiForNaver,
    val locationSearchApiForKakao: LocationSearchApiForKakao
) {

    fun search(request: LocationSearchRequest): LocationSearchResponse? {
        // naver API 요청
        val searchResponse = requestNaverLocationSearchApi(request)

        if (searchResponse.statusCode == HttpStatus.OK) {
            val test = searchResponse.body
            return searchResponse.body
        }

        throw CustomException(CustomErrorStatus.NO_SEARCH)
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