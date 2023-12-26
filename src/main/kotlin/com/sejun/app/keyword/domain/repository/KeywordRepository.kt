package com.sejun.app.keyword.domain.repository

import com.sejun.app.keyword.domain.Keyword
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface KeywordRepository : JpaRepository<Keyword, Long> {
    fun findByKeyword(keyword: String): Optional<Keyword>

    fun findAllByOrderByHitsDesc(pageable: Pageable): List<Keyword>
}