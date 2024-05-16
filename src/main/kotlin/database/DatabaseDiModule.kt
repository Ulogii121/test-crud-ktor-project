package database

import org.jetbrains.exposed.sql.Database
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseDiModule = module {

    single(named("userDatabase")) {
        UserDatabase(get()).configure("users")
    } bind Database::class

    single { DatabaseService(get(named("userDatabase"))) }
}