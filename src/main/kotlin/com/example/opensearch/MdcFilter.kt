package com.example.opensearch

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import java.util.*

@Component
class MdcFilter : Filter {
    val CORRELATION_ID = "correlationID"

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val start = System.currentTimeMillis()
        try {
            val correlationID = UUID.randomUUID().toString()
            MDC.put(CORRELATION_ID,correlationID)

            chain?.doFilter(request,response)
        } finally {
            val duration = System.currentTimeMillis() - start
            MDC.put("durationMs", duration.toString())
            MDC.clear()
        }
    }
}