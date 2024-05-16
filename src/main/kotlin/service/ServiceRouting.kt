package service

import database.DatabaseService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mu.KotlinLogging
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.koin
import utils.UserDto

val logger = KotlinLogging.logger { }

fun Application.serviceRouting() {

    koin {
        modules(serviceDiModule)
    }

    val appConfig: ApplicationConfig by inject()
    val databaseService: DatabaseService by inject()

    routing {
        post("/user") {
            runCatching {
                val request = call.receive<UserDto>().also {
                    logger.info { it }
                }
                val response = databaseService.createUser(request)
                call.respond(HttpStatusCode.Created, response)
            }.onFailure { logger.debug { it.stackTraceToString() } }
        }

        get("/user/{id}") {
            runCatching {
                val response = databaseService.getUserById(call.parameters["id"]!!.toInt())
                call.respond(HttpStatusCode.OK, response)
            }.onFailure { logger.debug { it.stackTraceToString() } }

        }

        put("/user") {
            runCatching {
                val request = call.receive<UserDto>().also {
                    logger.info { it }
                }
                databaseService.updateUser(request)
                val response = databaseService.getUserById(request.id!!.toInt())
                call.respond(HttpStatusCode.OK, response)
            }.onFailure { logger.debug { it.stackTraceToString() } }
        }

        delete("/user/{id}") {
            runCatching {
                databaseService.deleteUserById(call.parameters["id"]!!.toInt())
                call.respond(HttpStatusCode.NoContent)
            }.onFailure { logger.debug { it.stackTraceToString() } }
        }

        get("/users") {
            runCatching {
                val searchString = call.request.queryParameters["searchString"]
                val limit = call.request.queryParameters["limit"]?.toInt()
                val response = databaseService.getUsers(searchString, limit)
                call.respond(HttpStatusCode.OK, response)
            }.onFailure { logger.debug { it.stackTraceToString() } }
        }
    }
}
