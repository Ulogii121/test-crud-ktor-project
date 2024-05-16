package database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Sequence
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.nextIntVal
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import utils.UserDto

class DatabaseService(private val userDatabase: Database) {

    fun createUser(user: UserDto): UserDto =
        transaction(userDatabase) {
            UserTable.insert {
                it[this.id] = Sequence("user_id_seq").nextIntVal()
                it[this.login] = user.login
                it[this.firstName] = user.firstName
                it[this.surname] = user.surname
                it[this.patronymic] = user.patronymic
            }
        }.resultedValues?.first()?.toUserEntity()!!

    fun getUserById(id: Int): UserDto =
        transaction(userDatabase) {
            UserTable.select { UserTable.id eq id }.first().toUserEntity()
        }

    fun updateUser(user: UserDto) {
        transaction(userDatabase) {
            UserTable.update({ UserTable.id eq user.id!!.toInt() }) {
                it[this.login] = user.login
                it[this.firstName] = user.firstName
                it[this.surname] = user.surname
                it[this.patronymic] = user.patronymic
            }
        }
    }

    fun deleteUserById(id: Int) {
        transaction(userDatabase) {
            UserTable.deleteWhere { UserTable.id eq id }
        }
    }

    fun getUsers(searchString: String?, limit: Int?): List<UserDto> {
        val searchPattern = if (searchString != null) "$searchString%" else "%%"
        return transaction(userDatabase) {
            UserTable.select {
                UserTable.login like searchPattern
            }.limit(limit ?: 10).map {
                it.toUserEntity()
            }
        }
    }
}
