package com.sejun.app.keyword.presentation

import com.sejun.app.common.Response
import com.sejun.app.keyword.application.KeywordService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class KeywordController(
    val keywordService: KeywordService
) {

    @GetMapping("/keyword/{rank}")
    fun getKeywordRank(@PathVariable rank: Int): Response {
        return Response(200, "ok", keywordService.getRank(rank))
    }
}