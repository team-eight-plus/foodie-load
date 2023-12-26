package com.sejun.app.config

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration


@Configuration
class CircuitBreakerProvider(
    val circuitBreakerProperty: CircuitBreakerProperty
) {
    companion object {
        const val CIRCUIT_SEARCH: String = "SEARCH"
    }

    @Bean
    fun circuitBreakerRegistry(): CircuitBreakerRegistry {
        return CircuitBreakerRegistry.ofDefaults()
    }

    @Bean
    fun searchCircuitBreaker(circuitBreakerRegistry: CircuitBreakerRegistry): CircuitBreaker {
        return circuitBreakerRegistry.circuitBreaker(
            CIRCUIT_SEARCH, CircuitBreakerConfig.custom()
                .failureRateThreshold(circuitBreakerProperty.failureRateThreshold)
                .slowCallDurationThreshold(Duration.ofMillis(circuitBreakerProperty.slowCallDurationThreshold))
                .slowCallRateThreshold(circuitBreakerProperty.slowCallRateThreshold)
                .waitDurationInOpenState(Duration.ofMillis(circuitBreakerProperty.waitDurationInOpenState))
                .minimumNumberOfCalls(circuitBreakerProperty.minimumNumberOfCalls)
                .slidingWindowSize(circuitBreakerProperty.slidingWindowSize)
                .permittedNumberOfCallsInHalfOpenState(circuitBreakerProperty.permittedNumberOfCallsInHalfOpenState)
                .build()
        )
    }
}