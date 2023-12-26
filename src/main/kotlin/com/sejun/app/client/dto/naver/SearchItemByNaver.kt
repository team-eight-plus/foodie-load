package com.sejun.app.client.dto.naver

import com.sejun.app.client.dto.LocationSearchItems

data class SearchItemByNaver (
    val title: String,
    val link: String,
    val category: String,
    val description: String,
    val telephone: String,
    val address: String,
    val roadAddress: String,
    val mapx: Int,
    val mapy: Int
) {
    fun toLocationSearchItems(): LocationSearchItems {
        return LocationSearchItems(
            title = title,
            link= link,
            category = category,
            address = address,
            roadAddress = roadAddress,
            telephone = telephone
        )
    }
}