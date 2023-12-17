package com.sejun.app.exception

class CustomException(private val customErrorStatus: CustomErrorStatus) : RuntimeException(customErrorStatus.message) {

    val statusCode: Int = customErrorStatus.code

}
