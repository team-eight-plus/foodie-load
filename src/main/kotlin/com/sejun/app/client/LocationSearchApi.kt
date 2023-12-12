package com.sejun.app.client

import com.sejun.app.client.dto.LocationSearchRequestForNavar
import com.sejun.app.client.dto.SearchItemByNaver

interface LocationSearchApi {
    //TODO: interface를 사용하는 것에 대해 고민 필요.
    //      사용하게 된다면 dto도 interface로 변경 필요.
    //      추가적으로 api 요청하는 메서드도 interface에 구현 고려.
    fun search(request: LocationSearchRequestForNavar):
            List<SearchItemByNaver>;
}