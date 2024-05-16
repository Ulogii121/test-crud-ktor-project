package utils

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: Int? = null,
    val login: String,
    val firstName: String,
    val surname: String,
    val patronymic: String? = null
)
