package database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig

class UserDatabase(private val appConfig: ApplicationConfig) {

    companion object {
        private const val DEFAULT_POOL_SIZE = 1
    }

    fun configure(
        databaseName: String
    ) = Database.connect(
        HikariDataSource(
            HikariConfig().apply {
                this.driverClassName = appConfig.property("database.$databaseName.driver").getString()
                this.username = appConfig.property("database.$databaseName.username").getString()
                this.password = appConfig.property("database.$databaseName.password").getString()
                this.jdbcUrl = appConfig.property("database.$databaseName.url").getString()
                this.minimumIdle = 1
                this.maximumPoolSize =
                    appConfig.propertyOrNull("database.$databaseName.maximumPoolSize")?.getString()?.toIntOrNull()
                        ?: DEFAULT_POOL_SIZE
            }
        ),
        databaseConfig = DatabaseConfig()
    )
}
