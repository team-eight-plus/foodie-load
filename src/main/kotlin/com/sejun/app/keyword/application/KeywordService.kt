package com.sejun.app.keyword.application

import com.sejun.app.exception.CustomErrorStatus
import com.sejun.app.exception.CustomException
import com.sejun.app.keyword.domain.Keyword
import com.sejun.app.keyword.domain.repository.KeywordRepository
import com.sejun.app.keyword.presentation.KeywordRankResponse
import com.sejun.app.search.application.SearchEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class KeywordService(
    val keywordRepository: KeywordRepository
) {
    val log: Logger = LoggerFactory.getLogger(KeywordService::class.java)

    fun getRank(rank: Int): List<KeywordRankResponse> {
        if (rank < 0) {
            throw CustomException(CustomErrorStatus.ILLEGAL_RANK)
        }

        val keywords = keywordRepository.findAllByOrderByHitsDesc(PageRequest.of(0, rank, Sort.by("hits").descending()))

        return keywords.map{ it -> KeywordRankResponse.fromEntity(it)}
    }

    @Async
    @EventListener
    fun handleSearchEvent(event: SearchEvent) {
        val findByKeyword = keywordRepository.findByKeyword(event.keyword)
        when (findByKeyword.isPresent) {
            true -> {
                val keyword =findByKeyword.get()
                keyword.increaseHits()

                keywordRepository.save(keyword)
            }
            else -> {
                keywordRepository.save(Keyword(keyword = event.keyword, 1))
            }
        }
    }
}