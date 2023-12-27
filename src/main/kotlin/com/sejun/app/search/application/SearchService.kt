package com.sejun.app.search.application

import com.sejun.app.client.dto.LocationSearchRequest
import com.sejun.app.client.dto.LocationSearchResponse
import com.sejun.app.component.SearchManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class SearchService(
    val searchManager: SearchManager,
    val eventPublisher: ApplicationEventPublisher
) {
    val log: Logger = LoggerFactory.getLogger(SearchService::class.java)
    fun search(request: LocationSearchRequest): LocationSearchResponse {
        // naver API 요청
        // 키워드 저장
        val response = searchManager.search(request)
        eventPublisher.publishEvent(SearchEvent(keyword = request.query, response = response))
        return response
    }

    @Async
    @EventListener
    fun handleSearchEvent(event: SearchEvent) {
        log.info("search handleSearchEvent")
        val keyword = event.keyword
        val response = event.response
        //TODO: 검색 저장 필요
    }
}