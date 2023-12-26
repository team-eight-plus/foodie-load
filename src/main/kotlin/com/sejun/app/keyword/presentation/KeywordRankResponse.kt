package com.sejun.app.keyword.presentation

import com.sejun.app.keyword.domain.Keyword

class KeywordRankResponse(
    val keyword: String,
    val hits: Int
) {
    companion object {
        fun fromEntity(keyword: Keyword): KeywordRankResponse {
            return KeywordRankResponse(keyword = keyword.keyword, hits = keyword.hits)
        }
    }
}