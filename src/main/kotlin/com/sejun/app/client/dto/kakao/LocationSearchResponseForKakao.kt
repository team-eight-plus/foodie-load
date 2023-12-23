package com.sejun.app.client.dto.kakao

class LocationSearchResponseForKakao {
    var meta: Any
    var documents: List<Any>

    constructor(
        documents: List<Any>,
        meta: Any
    ) {
        this.documents = documents
        this.meta = meta
    }
}