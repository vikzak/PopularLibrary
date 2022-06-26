package ru.gb

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.gb.popularlibrary.data.FakeUsersRepositoryImpl
import ru.gb.popularlibrary.domain.repositories.UsersRepository

class App : Application() {
    val usersRepository: UsersRepository by lazy { FakeUsersRepositoryImpl() }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext() as App