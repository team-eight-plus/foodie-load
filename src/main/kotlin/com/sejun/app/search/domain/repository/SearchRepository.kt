package com.sejun.app.search.domain.repository

import com.sejun.app.search.domain.Search
import org.springframework.data.jpa.repository.JpaRepository

interface SearchRepository : JpaRepository<Search, Long> {
}