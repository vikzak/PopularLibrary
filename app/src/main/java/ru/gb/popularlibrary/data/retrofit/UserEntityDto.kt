package ru.gb.popularlibrary.data.retrofit

import com.google.gson.annotations.SerializedName
import ru.gb.popularlibrary.domain.entities.UserEntity

data class UserEntityDto(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("followers_url")
    val followersUrl: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("login")
    val login: String,
) {
    fun toUserEntity() = UserEntity(avatarUrl, login, id, followersUrl)

    companion object {
        fun fromUserEntity(userEntity: UserEntity): UserEntityDto {
            return UserEntityDto(
                userEntity.avatarUrl,
                userEntity.login,
                userEntity.id,
                userEntity.followersUrl
                )
        }
    }
}