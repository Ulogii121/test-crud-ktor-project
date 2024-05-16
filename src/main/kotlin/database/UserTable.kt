package database

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import utils.UserDto
import kotlin.math.log

object UserTable: Table("user") {
    val id = integer("id")
    val login = varchar("login", 20)
    val firstName = varchar("firstName", 40)
    val surname = varchar("surname", 40)
    val patronymic = varchar("patronymic", 40).nullable()

    override val primaryKey = PrimaryKey(id)
}

fun ResultRow.toUserEntity() = UserDto(
    id = this[UserTable.id],
    login = this[UserTable.login],
    firstName = this[UserTable.firstName],
    surname = this[UserTable.surname],
    patronymic = this[UserTable.patronymic]
)
