package com.sejun.app.keyword.domain

import com.sejun.app.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Keyword(
    val keyword: String,
    var hits: Int
): BaseEntity()  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun increaseHits() {
        hits++
    }
}