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
    }
}

