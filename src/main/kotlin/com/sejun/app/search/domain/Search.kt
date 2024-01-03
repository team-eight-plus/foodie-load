package com.sejun.app.search.domain

import com.sejun.app.client.dto.LocationSearchItems
import com.sejun.app.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Search(
    val keyword: String,
    val link: String,
    val category: String,
    val address: String,
    val roadAddress: String,
    val telephone: String
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    companion object {
        fun convertSearchByLocationSearchItems(target: LocationSearchItems): Search {
            return Search(
                keyword = target.keyword,
                link = target.link,
                category = target.category,
                address = target.address,
                roadAddress = target.roadAddress,
                telephone = target.telephone
            )
        }
    }

}