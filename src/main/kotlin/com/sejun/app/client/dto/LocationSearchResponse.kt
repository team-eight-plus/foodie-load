package com.sejun.app.client.dto

import com.sejun.app.client.dto.kakao.LocationSearchResponseForKakao
import com.sejun.app.client.dto.naver.LocationSearchResponseForNaver
import com.sejun.app.common.constants.LocationSearch
import com.sejun.app.exception.CustomErrorStatus
import com.sejun.app.exception.CustomException
import org.springframework.http.ResponseEntity

data class LocationSearchResponse(
    val status: Int,
    val items: List<LocationSearchItems> = ArrayList()
) {
    companion object {
        fun <T> convertToLocationSearchResponseByResponseEntity(
            platformName: String, responseEntity: ResponseEntity<T>): LocationSearchResponse {
            if (responseEntity.statusCode.isError) {
                return LocationSearchResponse(status = responseEntity.statusCode.value())
            }

            return when (platformName) {
                LocationSearch.NAVER -> {
                    val body = responseEntity.body as LocationSearchResponseForNaver
                    LocationSearchResponse(status = responseEntity.statusCode.value(),
                        body?.toLocationSearchItems() ?: ArrayList())
                }
                LocationSearch.KAKAO -> {
                    val body = responseEntity.body as LocationSearchResponseForKakao
                    LocationSearchResponse(status = responseEntity.statusCode.value(),
                        body?.toLocationSearchItems() ?: ArrayList())
                }
                else -> {
                    throw CustomException(CustomErrorStatus.NO_SEARCH)
                }
            }
        }
    }

}

