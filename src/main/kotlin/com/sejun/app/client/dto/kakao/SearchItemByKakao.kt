package com.sejun.app.client.dto.kakao

import com.fasterxml.jackson.annotation.JsonAlias
import com.sejun.app.client.dto.LocationSearchItems

data class SearchItemByKakao (
    val id: String,

    @JsonAlias("place_name")
    val placeName: String = "",

    @JsonAlias("category_name")
    val categoryName: String = "",

    @JsonAlias("category_group_code")
    val categoryGroupCode: String = "",

    @JsonAlias("category_group_name")
    val categoryGroupName: String = "",

    val phone: String = "",

    @JsonAlias("address_name")
    val addressName: String = "",

    @JsonAlias("road_address_name")
    val roadAddressName: String = "",

    val x: String = "",

    val y: String = "",

    @JsonAlias("place_url")
    val placeUrl: String = "",

    val distance: String = ""
) {
    fun toLocationSearchItems(): LocationSearchItems {

        return LocationSearchItems(
            title = placeName,
            link = placeUrl,
            category = categoryName,
            address = addressName,
            roadAddress = roadAddressName,
            telephone = phone
        )
    }
}