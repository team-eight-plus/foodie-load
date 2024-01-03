package com.sejun.app.keyword.application

import com.sejun.app.exception.CustomErrorStatus
import com.sejun.app.exception.CustomException
import com.sejun.app.keyword.domain.repository.KeywordRepository
import com.sejun.app.keyword.presentation.KeywordRankResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class KeywordService(
    val keywordRepository: KeywordRepository
) {
    val log: Logger = LoggerFactory.getLogger(KeywordService::class.java)

    companion object {
        private const val POPULAR_KEYWORD = "POPULAR_KEYWORD"
    }

    @Cacheable(POPULAR_KEYWORD)
    fun getRank(rank: Int): List<KeywordRankResponse> {
        if (rank < 0) {
            throw CustomException(CustomErrorStatus.ILLEGAL_RANK)
        }

        val keywords = keywordRepository.findAllByOrderByHitsDesc(PageRequest.of(0, rank, Sort.by("hits").descending()))

        return keywords.map{ it -> KeywordRankResponse.fromEntity(it)}
    }

    @CacheEvict(POPULAR_KEYWORD)
    @Scheduled(cron = "* */10 * * * *")
    fun evictPopularKeywordCache() {
    }
}