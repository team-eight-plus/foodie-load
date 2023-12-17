package com.sejun.app.common

class Response(
    val status: Int,
    val message: String,
    val data: Any? = null
)