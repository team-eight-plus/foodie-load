package com.sejun.app.component

import com.sejun.app.keyword.domain.Keyword
import com.sejun.app.keyword.domain.repository.KeywordRepository
import com.sejun.app.search.application.SearchEvent
import com.sejun.app.search.domain.Search
import com.sejun.app.search.domain.repository.SearchRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class SearchEventListener(
    private val keywordRepository: KeywordRepository,
    private val searchRepository: SearchRepository
) {
    val log: Logger = LoggerFactory.getLogger(SearchEventListener::class.java)

    @TransactionalEventListener
    fun saveKeyword(event: SearchEvent) {
        try {
            val findByKeyword = keywordRepository.findByKeyword(event.keyword)
            when (findByKeyword.isPresent) {
                true -> {
                    val keyword = findByKeyword.get()
                    keyword.increaseHits()

                    keywordRepository.save(keyword)
                }
                else -> {
                    keywordRepository.save(Keyword(keyword = event.keyword, 1))
                }
            }
        } catch (e: Exception) {
            log.error("saveKeyword error !! ${e.message}")
        }
    }

    @EventListener
    fun saveSearch(event: SearchEvent) {
        try {
            val result = event.response

            if (result.status != 200) {
                return
            }

            val items = result.items
            if (items.isNotEmpty()) {
                searchRepository.saveAll(items.map { Search.convertSearchByLocationSearchItems(it) })
            }
        } catch (e: Exception) {
            log.error("saveSearch error !! ${e.message}")
        }
    }
}