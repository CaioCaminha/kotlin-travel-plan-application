package com.caminha.kotlintravelplanapi.application.gateway.r2dbc.common

import com.google.cloud.Timestamp
import org.springframework.data.domain.Persistable
import java.io.Serializable

interface PersistableEntity<T>: Serializable, Persistable<T> {
    val createdAt: Timestamp
    var updatedAt: Timestamp

    override fun isNew(): Boolean {
        return createdAt == updatedAt
    }

    fun updated() {
        updatedAt = Timestamp.now()
    }

}

// this is a generic extension function with the generic type R -> a class that implements PersistableEntity
fun <R : PersistableEntity<*>> R.toUpdated(): R {
    this.updated()
    return this
}