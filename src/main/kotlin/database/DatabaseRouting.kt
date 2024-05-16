package database

import io.ktor.server.application.*
import org.koin.ktor.plugin.koin

fun Application.databaseRouting() {

    koin { modules(databaseDiModule) }
}