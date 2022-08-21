package ru.gb.popularlibrary.data.retrofit

import android.os.Handler
import android.os.Looper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.gb.popularlibrary.domain.entities.UserEntity
import ru.gb.popularlibrary.domain.repositories.UsersRepository


private const val DATA_LOADINGFAKE_DELAY = 1000L

class RetrofitUsersRepositoryImpl : UsersRepository {

//    private val data: List<UserEntity> = listOf(
//        UserEntity("https://avatars.githubusercontent.com/u/1?v=4", 1, "mojombo", "MDQ6VXNlcjE="),
//        UserEntity("https://avatars.githubusercontent.com/u/2?v=4", 2, "defunkt", "MDQ6VXNlcjI="),
//        UserEntity("https://avatars.githubusercontent.com/u/3?v=4", 3, "pjhyett", "MDQ6VXNlcjM="),
//        UserEntity("https://avatars.githubusercontent.com/u/4?v=4", 4, "wycats", "MDQ6VXNlcjQ="),
//
//        )
    // использование Gson
    private val retrofit: Retrofit? = Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

    // использование Moshi
//    private val retrofit: Retrofit? = Retrofit.Builder()
//        .baseUrl("https://api.github.com/")
//        .addConverterFactory(MoshiConverterFactory.create())
//        .build()

    private val apiGithub: GithubApi = retrofit!!.create(GithubApi::class.java)

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        apiGithub.getUsers().enqueue(object : Callback<List<UserEntity>>{
            override fun onResponse(
                call: Call<List<UserEntity>>,
                response: Response<List<UserEntity>>
            ) {
                val body = response.body()
                if (response.isSuccessful && body!=null){
                    onSuccess.invoke(body)
                } else {
                    if (onError != null) {
                        onError.invoke(IllegalStateException("No data"))
                    }
                }
            }

            override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
                onError?.invoke(IllegalStateException("No data"))
            }

        })
    }
}




