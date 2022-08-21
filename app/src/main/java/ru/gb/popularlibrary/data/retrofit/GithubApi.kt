package ru.gb.popularlibrary.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.gb.popularlibrary.domain.entities.UserEntity


interface GithubApi {
    //@GET("users/{user}/repos")
    @GET("users")
    fun getUsers(): Call<List<UserEntity>>
}