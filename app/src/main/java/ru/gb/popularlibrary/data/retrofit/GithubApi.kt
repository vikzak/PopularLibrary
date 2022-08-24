package ru.gb.popularlibrary.data.retrofit



import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.gb.popularlibrary.domain.entities.UserEntity

// было до реактивщины
//interface GithubApi {
//    //@GET("users/{user}/repos")
//    @GET("users")
//    fun getUsers(): Call<List<UserEntity>>
//}

interface GithubApi {
    @GET("users")
    fun getUsers(): Single<List<UserEntity>>
}