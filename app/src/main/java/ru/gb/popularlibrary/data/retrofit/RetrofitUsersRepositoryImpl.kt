package ru.gb.popularlibrary.data.retrofit

import android.os.Handler
import android.os.Looper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.gb.popularlibrary.domain.entities.UserEntity
import ru.gb.popularlibrary.domain.repositories.UsersRepository


private const val DATA_LOADINGFAKE_DELAY = 1000L

class RetrofitUsersRepositoryImpl : UsersRepository {

    // использование Gson
    private val retrofit: Retrofit? = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    // использование Moshi
//    private val retrofit: Retrofit? = Retrofit.Builder()
//        .baseUrl("https://api.github.com/")
//        .addConverterFactory(MoshiConverterFactory.create())
//        .build()

    private val apiGithub: GithubApi = retrofit!!.create(GithubApi::class.java)

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
//        apiGithub.getUsers().enqueue(object : Callback<List<UserEntity>> {
//            override fun onResponse(
//                call: Call<List<UserEntity>>,
//                response: Response<List<UserEntity>>
//            ) {
//                val body = response.body()
//                if (response.isSuccessful && body != null) {
//                    onSuccess.invoke(body)
//                } else {
//                    if (onError != null) {
//                        onError.invoke(IllegalStateException("No data"))
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
//                onError?.invoke(IllegalStateException("No data"))
//            }
//
//        })
        apiGithub.getUsers().subscribe {
            onSuccess.invoke(it)
        }
    }
}




