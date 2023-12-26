package com.sejun.app.search.application

import com.sejun.app.client.dto.LocationSearchRequest
import com.sejun.app.client.dto.LocationSearchResponse
import com.sejun.app.component.SearchManager
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class SearchService(
    val searchManager: SearchManager,
    val eventPublisher: ApplicationEventPublisher
) {

    fun search(request: LocationSearchRequest): LocationSearchResponse? {
        // naver API 요청
        // 키워드 저장
        //TODO: search 처리 후 publishEvent가 실행될 수 있도록 aop적용 고려
        val response = searchManager.search(request)
        eventPublisher.publishEvent(SearchEvent(request.query))
        return response
    }
}