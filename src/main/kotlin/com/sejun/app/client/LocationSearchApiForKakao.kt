package com.sejun.app.client

import com.sejun.app.client.dto.*
import com.sejun.app.client.dto.kakao.LocationSearchRequestForKakao
import com.sejun.app.client.dto.kakao.LocationSearchResponseForKakao
import com.sejun.app.common.constants.LocationSearch
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@Component
class LocationSearchApiForKakao(
    @Value("\${search.client.kakao.key}") private val key: String,
) {
    fun search(request: LocationSearchRequestForKakao): LocationSearchResponse {
        request.validate()

        val response = requestLocationSearchApi(createRequestEntity(createURI(request)))
        return LocationSearchResponse.convertToLocationSearchResponseByResponseEntity("KAKAO", response)
    }

    fun requestLocationSearchApi(requestEntity: RequestEntity<Void>): ResponseEntity<LocationSearchResponseForKakao> {
        return RestTemplate().exchange(requestEntity, LocationSearchResponseForKakao::class.java)
    }

    fun createURI(request: LocationSearchRequestForKakao): URI {
        return UriComponentsBuilder
            .fromUriString(LocationSearch.KAKAO_API_URL)
            .path(LocationSearch.KAKAO_PATH_JSON)
            .queryParam("query", request.query)
            .encode()
            .build()
            .toUri();
    }

    fun createRequestEntity(uri: URI): RequestEntity<Void> {
        return RequestEntity
            .get(uri)
            .header(LocationSearch.KAKAO_AUTHORIZATION_HEADER, key)
            .build()
    }
}