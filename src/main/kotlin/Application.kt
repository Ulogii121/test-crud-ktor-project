import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.defaultheaders.*
import kotlinx.serialization.json.Json
import mu.KotlinLogging
import org.koin.dsl.bind
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {

    install(DefaultHeaders)

    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }

    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
        allowNonSimpleContentTypes = true
    }

    val mainDiModule = org.koin.dsl.module {
        single { this@module.environment.config } bind ApplicationConfig::class
    }

    install(Koin) {
        modules(mainDiModule)
    }
}

val appLogger = KotlinLogging.logger { }