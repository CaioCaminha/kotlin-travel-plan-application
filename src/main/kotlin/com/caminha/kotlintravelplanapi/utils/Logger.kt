package com.caminha.kotlintravelplanapi.utils

import org.slf4j.LoggerFactory

class Logger

fun <T : Any> T.logInfo(msg: String, methodName: String = getMethodName())  = getLogger()
    .info(Thread.currentThread().run {"$methodName -> $msg"})
fun <T : Any> T.logWarn(msg: String, ex: Throwable, methodName: String = getMethodName()) =
    getLogger().warn("$methodName -> $msg", ex)
fun <T : Any> T.logWarn(msg: String, methodName: String = getMethodName()) =
    getLogger().warn("$methodName -> $msg")
fun <T : Any> T.logError(msg: String, ex: Throwable, methodName: String = getMethodName()) =
    getLogger().error("$methodName -> $msg", ex)
fun <T : Any> T.logError(msg: String, methodName: String = getMethodName()) =
    getLogger().error("$methodName -> $msg")
fun <T : Any> T.logDebug(msg: String, methodName: String = getMethodName()) = getLogger()
    .debug(Thread.currentThread().run { "$methodName -> $msg" })



private fun <T : Any> T.getMethodName(): String = runCatching {
    Thread.currentThread().stackTrace.first().javaClass.simpleName
}.getOrNull() ?: javaClass.simpleName


private fun <T : Any> T.getLogger() = LoggerFactory.getLogger(javaClass.canonicalName ?: javaClass.simpleName)