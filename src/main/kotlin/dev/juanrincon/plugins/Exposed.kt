package dev.juanrincon.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dev.juanrincon.domain.daos.AreaTable
import dev.juanrincon.domain.daos.ProjectStatusTable
import dev.juanrincon.domain.daos.ResourceTable
import dev.juanrincon.domain.daos.UserTable
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureExposed() {
    Database.connect(hikari(environment))

    transaction {
        SchemaUtils.create(
            UserTable,
            ResourceTable,
            AreaTable,
            ProjectStatusTable,
        )
    }
}

private fun hikari(environment: ApplicationEnvironment) = HikariDataSource(HikariConfig().apply {
    driverClassName = environment.config.propertyOrNull("ktor.database.driver")?.getString()
    jdbcUrl = environment.config.propertyOrNull("ktor.database.host")?.getString()
    maximumPoolSize = 3
    isAutoCommit = false
    transactionIsolation = "TRANSACTION_REPEATABLE_READ"
    val user = environment.config.propertyOrNull("ktor.database.user")?.getString()
    if (user != null) {
        username = user
    }
    val dbPassword = environment.config.propertyOrNull("ktor.database.password")?.getString()
    if (dbPassword != null) {
        password = dbPassword
    }
    validate()
})

suspend fun <T> dbQuery(block: () -> T): T =
    withContext(Dispatchers.IO) {
        transaction { block() }
    }