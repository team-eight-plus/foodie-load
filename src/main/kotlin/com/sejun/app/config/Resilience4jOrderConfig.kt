package com.sejun.app.config

import io.github.resilience4j.spring6.circuitbreaker.configure.CircuitBreakerConfigurationProperties
import io.github.resilience4j.spring6.retry.configure.RetryConfigurationProperties
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration

@Configuration
class Resilience4jOrderConfig(
    val circuitBreakerConfigurationProperties: CircuitBreakerConfigurationProperties,
    val retryConfigurationProperties: RetryConfigurationProperties
) {
    companion object {
        const val PRIORITY_1 = -3
        const val PRIORITY_2 = -4
    }

    @PostConstruct
    fun setOrder() {
        circuitBreakerConfigurationProperties.circuitBreakerAspectOrder = PRIORITY_2
        retryConfigurationProperties.retryAspectOrder = PRIORITY_1
    }
}