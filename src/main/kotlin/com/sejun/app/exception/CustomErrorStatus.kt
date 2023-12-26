package com.sejun.app.exception

import jakarta.servlet.http.HttpServletResponse
import lombok.AllArgsConstructor

@AllArgsConstructor
data class CustomErrorStatus(
    val code: Int,
    val message: String
) {
    companion object {
        val NO_SEARCH = CustomErrorStatus(HttpServletResponse.SC_NOT_FOUND, "검색 실패.")
        val NO_KEYWORD = CustomErrorStatus(HttpServletResponse.SC_BAD_REQUEST, "검색어 없음.")
        val ILLEGAL_RANK = CustomErrorStatus(HttpServletResponse.SC_BAD_REQUEST, "rank는 0보다 작을 수 없음.")
    }
}

