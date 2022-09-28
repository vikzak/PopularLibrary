package ru.gb.popularlibrary.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.popularlibrary.data.retrofit.GithubApi
import ru.gb.popularlibrary.data.retrofit.RetrofitUsersRepositoryImpl
import ru.gb.popularlibrary.domain.repositories.UsersRepository
import ru.gb.popularlibrary.ui.users.UsersViewModel


val appModule = module {
    single(qualifier("url")) { "https://api.github.com/" }
    single(named("name")) { "test" }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("url")))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    factory<GithubApi> { get<Retrofit>().create(GithubApi::class.java) }
    single<UsersRepository> { RetrofitUsersRepositoryImpl(get()) }

    viewModel {
        UsersViewModel(get())
    }

}
