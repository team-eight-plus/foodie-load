package com.sejun.app.search.application

import com.sejun.app.client.dto.LocationSearchRequest
import com.sejun.app.client.dto.LocationSearchResponse
import com.sejun.app.component.SearchManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class SearchService(
    val searchManager: SearchManager,
    val eventPublisher: ApplicationEventPublisher
) {
    val log: Logger = LoggerFactory.getLogger(SearchService::class.java)
    fun search(request: LocationSearchRequest): LocationSearchResponse {
        val response = searchManager.search(request)

        eventPublisher.publishEvent(SearchEvent(keyword = request.query, response = response))
        return response
    }
}