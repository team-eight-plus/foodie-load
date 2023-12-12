package com.sejun.app.client

import com.sejun.app.client.dto.LocationSearchRequestForNavar
import com.sejun.app.client.dto.LocationSearchResponseForNavar
import com.sejun.app.client.dto.SearchItemByNaver
import com.sejun.app.common.constants.LocationSearch
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.util.*
@Component
class NaverLocationSearchApi : LocationSearchApi {

    override fun search(request: LocationSearchRequestForNavar):
            List<SearchItemByNaver> {
        val uri = createURI(request)
        val requestEntity = createRequestEntity(uri)
        val response = requestLocationSearchApi(requestEntity)

        //TODO: 예외처리 필요
        return response!!.items
    }

    fun requestLocationSearchApi(requestEntity: RequestEntity<Void>): LocationSearchResponseForNavar? {
        //TODO: return value LocationSearchResponseForNavar null 여부에 대해 확인필요.
        // 이 부분에서 예외처리가 있어야 할 것 같음.
        return RestTemplate().exchange(
            requestEntity, LocationSearchResponseForNavar::class.java).getBody()
    }

    fun createURI(request: LocationSearchRequestForNavar): URI {
        //TODO: request:LocationSearchRequestForNavar의 값으로 queryString 생성 필요
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
        //TODO: key 관리
        return RequestEntity
            .get(uri)
            .header(LocationSearch.NAVER_CLIENT_ID_HEADER, "x6tmsVBuLuVZnCtS0iI0")
            .header(LocationSearch.NAVER_CLIENT_KEY_HEADER, "tFjClHjwzs")
            .build()
    }
}