package com.sejun.app.search.application

import com.sejun.app.client.dto.*
import com.sejun.app.component.SearchManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service
import java.sql.DriverManager.println

@Service
class SearchService(
    val searchManager: SearchManager
) {

    fun search(request: LocationSearchRequest): LocationSearchResponse? {
        // naver API 요청
        // 키워드 저장
        val response = searchManager.search(request)

        return response.body
    }
}