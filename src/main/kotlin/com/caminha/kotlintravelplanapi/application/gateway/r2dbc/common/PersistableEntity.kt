package com.caminha.kotlintravelplanapi.application.gateway.r2dbc.common

import com.google.cloud.Timestamp
import org.springframework.data.domain.Persistable
import java.io.Serializable

interface PersistableEntity<T> : Serializable, Persistable<T> {
    val createdAt: Timestamp
    var updatedAt: Timestamp

    override fun isNew(): Boolean {
        return createdAt == updatedAt
    }

    fun updated() {
        updatedAt = Timestamp.now()
    }

}

//defines a receiver R that implements PersistableEntity and declare function toUpdated
fun <R: PersistableEntity<*>> R.toUpdated(): R {
    this.updated()
    return this
}
