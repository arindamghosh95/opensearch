package com.example.opensearch

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.ThreadContext
import org.slf4j.MDC
import java.util.*
import kotlin.reflect.full.companionObject

class AppLogger(
    forClass: Class<*> = AppLogger::class.java,
) {
    private val CORRELATION_ID = "correlationID"
    private val logObject = LogManager.getLogger(getClassForLogging(forClass))

    fun info(message:String,
             additionalTags: Map<String?, String?> = Collections.emptyMap()
    ){
        logMessage(message, additionalTags)
    }

    private fun logMessage(message: String, additionalTags: Map<String?, String?>) {
        additionalTags.forEach { (key, value) -> MDC.put(key, value) }
        ThreadContext.putAll(additionalTags)
        logObject.info(message)
        MDC.clear()
    }

    private fun <T : Any> getClassForLogging(javaClass: Class<T>): Class<*> =
        javaClass.enclosingClass?.takeIf { it.kotlin.companionObject?.java == javaClass } ?: javaClass
}