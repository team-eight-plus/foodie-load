package com.sejun.app.client

import com.sejun.app.client.dto.naver.LocationSearchRequestForNaver
import com.sejun.app.common.constants.LocationSearch
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import java.net.URI

@SpringBootTest
internal class LocationSearchApiForNaverTest {

    @Autowired
    private lateinit var locationSearchApiForNaver: LocationSearchApiForNaver

    @Value("\${search.client.naver.id}")
    private lateinit var clientId: String

    @Value("\${search.client.naver.key}")
    private lateinit var clientKey: String

    @Test
    fun createURITest() {
        val request = LocationSearchRequestForNaver(query = "")

        val expectedURI = URI.create(
            LocationSearch.NAVER_API_URL + LocationSearch.NAVER_PATH_JSON + "?query=&display=5&start=1&sort=random");
        val createURI = locationSearchApiForNaver.createURI(request)

        assertThat(createURI).isEqualTo(expectedURI)
    }

    fun createURITestFailure() {
    }

    @Test
    fun createRequestEntityTest() {
        val request = LocationSearchRequestForNaver(query = "")
        val uri = locationSearchApiForNaver.createURI(request)

        val requestEntity = locationSearchApiForNaver.createRequestEntity(uri)

        assertThat(requestEntity.method).isEqualTo(HttpMethod.GET)
        assertThat(requestEntity.url).isEqualTo(uri)

        assertThat(requestEntity.headers.containsKey(LocationSearch.NAVER_CLIENT_ID_HEADER)).isTrue
        assertThat(requestEntity.headers.containsKey(LocationSearch.NAVER_CLIENT_KEY_HEADER)).isTrue

        assertThat(requestEntity.headers.getFirst(LocationSearch.NAVER_CLIENT_ID_HEADER)).isEqualTo(clientId)
        assertThat(requestEntity.headers.getFirst(LocationSearch.NAVER_CLIENT_KEY_HEADER)).isEqualTo(clientKey)

    }

    @Test
    fun requestLocationSearchApiTest() {

    }

    @Test
    fun searchTest() {
    }
}