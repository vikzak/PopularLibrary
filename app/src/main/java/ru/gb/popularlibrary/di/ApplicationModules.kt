package ru.gb.popularlibrary.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.popularlibrary.data.retrofit.GithubApi
import ru.gb.popularlibrary.data.retrofit.RetrofitUsersRepositoryImpl
import ru.gb.popularlibrary.domain.repositories.UsersRepository
import ru.gb.popularlibrary.ui.users.UsersViewModel

@Module
class ApplicationModules {
    private val baseUrl = "https://api.github.com/"

    @Provides
    fun providerRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun providerGithubApi(retrofit: Retrofit ): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Provides
    fun providerUsersRepository(api: GithubApi): UsersRepository {
        return RetrofitUsersRepositoryImpl(api)
    }
}