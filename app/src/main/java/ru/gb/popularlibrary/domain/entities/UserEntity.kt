package ru.gb.popularlibrary.domain.entities

data class UserEntity(
    val avatarUrl: String,
    val login: String,
    val id: Long,
    val followersUrl: String
)