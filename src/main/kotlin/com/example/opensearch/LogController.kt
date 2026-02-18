package com.example.opensearch

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.slf4j.Logger

@RestController
@RequestMapping("/api")
class LogController {

    @PostMapping("/echo")
    fun echo(@RequestBody(required = false) body: String?, req: HttpServletRequest):ResponseEntity<Any> {
       logger.info("body $body")
        return ResponseEntity.ok("ok")
    }

    companion object{
        private val logger = AppLogger(this::class.java)
    }
}