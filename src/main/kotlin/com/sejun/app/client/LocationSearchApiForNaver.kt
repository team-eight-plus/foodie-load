package com.sejun.app.client

import com.sejun.app.client.dto.LocationSearchRequest
import com.sejun.app.client.dto.LocationSearchResponse
import com.sejun.app.client.dto.naver.LocationSearchRequestForNaver
import com.sejun.app.client.dto.naver.LocationSearchResponseForNaver
import com.sejun.app.common.constants.LocationSearch
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.util.*

@Component
class LocationSearchApiForNaver(
    @Value("\${search.client.naver.id}") private val clientId: String,
    @Value("\${search.client.naver.key}") private val clientKey: String
) : LocationSearchApi {

    override fun search(request: LocationSearchRequest): ResponseEntity<LocationSearchResponse> {
         return requestLocationSearchApi(createRequestEntity(createURI(request as LocationSearchRequestForNaver)))
    }

    fun requestLocationSearchApi(requestEntity: RequestEntity<Void>): ResponseEntity<LocationSearchResponse> {
        val response =
            RestTemplate().exchange(requestEntity, LocationSearchResponseForNaver::class.java)

        if (response.statusCode == HttpStatus.OK && response.body != null) {
            val content = response.body
            content!!.items.forEach{
            }
        }
        return RestTemplate().exchange(requestEntity, LocationSearchResponseForNaver::class.java) as ResponseEntity<LocationSearchResponse>
    }

    fun createURI(request: LocationSearchRequestForNaver): URI {
        return UriComponentsBuilder
            .fromUriString(LocationSearch.NAVER_API_URL)
            .path(LocationSearch.NAVER_PATH_JSON)
            .queryParam("query", request.query)
            .queryParam("display", request.display)
            .queryParam("start", request.start)
            .queryParam("sort", request.sort)
            .encode()
            .build()
            .toUri();
    }

    fun createRequestEntity(uri: URI): RequestEntity<Void> {
        return RequestEntity
            .get(uri)
            .header(LocationSearch.NAVER_CLIENT_ID_HEADER, clientId)
            .header(LocationSearch.NAVER_CLIENT_KEY_HEADER, clientKey)
            .build()
    }
}