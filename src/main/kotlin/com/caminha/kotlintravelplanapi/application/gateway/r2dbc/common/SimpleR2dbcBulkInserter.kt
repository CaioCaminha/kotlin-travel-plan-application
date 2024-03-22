package com.caminha.kotlintravelplanapi.application.gateway.r2dbc.common

import io.r2dbc.spi.Connection
import io.r2dbc.spi.Statement
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactor.asFlux
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.core.StatementMapper
import org.springframework.data.r2dbc.dialect.DialectResolver
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity
import org.springframework.r2dbc.core.PreparedOperation
import org.springframework.r2dbc.core.binding.BindTarget

class SimpleR2dbcBulkInserter(
    private val r2dbcEntityTemplate: R2dbcEntityTemplate,
) {

    private val dataAccessStrategy = DefaultReactiveDataAccessStrategy(
        DialectResolver.getDialect(r2dbcEntityTemplate.databaseClient.connectionFactory)
    )

    private fun <T : Any> getInsertOperation(entity: T): PreparedOperation<*> {
        val requiredPersistentEntity:  RelationalPersistentEntity<*> = dataAccessStrategy.mappingContext.getRequiredPersistentEntity(entity::class.java)

        val outboundRow = dataAccessStrategy.getOutboundRow(entity)

        val tableName = requiredPersistentEntity.qualifiedTableName
        var insert: StatementMapper.InsertSpec = dataAccessStrategy.statementMapper.createInsert(tableName)

        for (column in outboundRow.keys) {
            val settableValue = outboundRow[column]
            if(settableValue.hasValue()) {
                insert = insert.withColumn(column!!, settableValue)
            }
        }

        return dataAccessStrategy.statementMapper.getMappedObject(insert)
    }

    private fun <T : Any> R2dbcEntityTemplate.executeFlowInConnection(
        action: (Connection) -> Flow<T>
    ): Flow<T> {
        return databaseClient.inConnectionMany {
            action(it).asFlux()
        }.asFlow()
    }


    internal class StatementWrapper(val statement: Statement) : BindTarget {
        override fun bind(identifier: String, value: Any) {
            statement.bind(identifier, value)
        }

        override fun bind(index: Int, value: Any) {
            statement.bind(index, value)
        }

        override fun bindNull(identifier: String, type: Class<*>) {
            statement.bindNull(identifier, type)
        }

        override fun bindNull(index: Int, type: Class<*>) {
            statement.bindNull(index, type)
        }
    }

    fun <T : Any> insertAll(entities: Collection<T>): Flow<T> {
        if(entities.isEmpty()) return emptyFlow()

        return r2dbcEntityTemplate.executeFlowInConnection {connection ->
            flow {
                val query = getInsertOperation(entities.first()).toQuery()
                val statement = StatementWrapper(connection.createStatement(query))
                entities.forEach {
                    val operation = getInsertOperation(it)
                    operation.bindTo(statement)
                    statement.statement.add()
                }

                val result = statement.statement.execute().asFlow().onEach {
                    val count = it.rowsUpdated.awaitSingle()
                    if (count != 1L) throw InvalidQueryResult("Invalid result: No columns affected for query: $query")
                }.count()

                if (entities.size != result) throw InvalidQueryResult(
                    "Invalid result: Expected ${entities.size} queries executed but got $result query: $query"
                )

                entities.forEach { emit(it) }
            }
        }
    }

}

data class InvalidQueryResult(override val message: String) : Exception(message)

fun <T : Any> R2dbcEntityTemplate.insertAll(
    entities: Collection<T>
) = SimpleR2dbcBulkInserter(this).insertAll(entities)