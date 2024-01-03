package com.sejun.app.search.presentation.controller

import com.sejun.app.client.dto.LocationSearchRequest
import com.sejun.app.common.Response
import com.sejun.app.search.application.SearchService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController(
    val searchService: SearchService,
) {
    val log: Logger = LoggerFactory.getLogger(SearchController::class.java)

    @GetMapping("/search")
    fun search(@RequestParam query: String): Response {
        return Response(200, "success",
            searchService.search(LocationSearchRequest(query = query)))
    }
}