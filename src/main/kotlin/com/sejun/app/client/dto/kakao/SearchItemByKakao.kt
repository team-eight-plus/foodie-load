package com.sejun.app.client.dto.kakao

data class SearchItemByKakao (
    val id: String,
    val placeName: String,
    val categoryName: String,
    val categoryGroupCode: String,
    val categoryGroupName: String,
    val phone: String,
    val addressName: String,
    val roadAddressName: String,
    val x: String,
    val y: String,
    val placeUrl: String,
    val distance: String
)