package ru.gb

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.popularlibrary.data.LocalUsersRepositoryImpl
import ru.gb.popularlibrary.data.retrofit.GithubApi
import ru.gb.popularlibrary.data.retrofit.RetrofitUsersRepositoryImpl
import ru.gb.popularlibrary.domain.repositories.UsersRepository

class App : Application() {
    //local
//    val usersRepository: UsersRepository by lazy { LocalUsersRepositoryImpl() }

    // retrofit
    private val baseUrl = "https://api.github.com/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    private val apiGithub: GithubApi by lazy { retrofit.create(GithubApi::class.java) }

    private val uiHandler:Handler by lazy { Handler(Looper.getMainLooper()) }

    val usersRepository: UsersRepository by lazy { RetrofitUsersRepositoryImpl(apiGithub) }
    //val usersRepository: UsersRepository by lazy { LocalUsersRepositoryImpl(uiHandler) }

}


val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext() as App