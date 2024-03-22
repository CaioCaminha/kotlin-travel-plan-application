package com.caminha.kotlintravelplanapi.application.gateway.r2dbc.common

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.r2dbc.spi.Row
import com.google.cloud.Timestamp

fun timeStampDeserializer() = objecDeserializer {p, _ ->
    Timestamp.parseTimestamp(p.text)
}

inline fun <reified T> objecDeserializer(
    crossinline deserializer: (JsonParser, DeserializationContext) -> T,
) = object : StdDeserializer<T>(T::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): T {
        return deserializer(p, ctxt)
    }
}

val r2dbcObjectMapper: ObjectMapper = jacksonObjectMapper()
    .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    .registerModule(
        SimpleModule().also {
            it.addDeserializer(Timestamp::class.java, timeStampDeserializer())
        }
    )

inline fun <reified T> Row.jsonToObject(field: String): T {
    return r2dbcObjectMapper.readValue(this[field].toString(), T::class.java)
}

inline fun <reified T> Row.jsonArrayToObject(field: String): Collection<T> {
    return jsonToObject<List<LinkedHashMap<Any, Any>>>(field)
        .map { r2dbcObjectMapper.writeValueAsString(it) }
        .map { r2dbcObjectMapper.readValue(it, T::class.java) }
}

inline fun <reified T> Row.read(field: String): T {
    return this[field].toString() as T
}

inline fun <reified T> String.toJsonObject(): T {
    return r2dbcObjectMapper.readValue(this, T::class.java)
}