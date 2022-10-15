package ru.gb.popularlibrary.di

import dagger.Component
import ru.gb.popularlibrary.domain.repositories.UsersRepository


@Component(modules = [ApplicationModules::class])
interface ApplicationComponent {
    fun getUsersRepository():UsersRepository

}