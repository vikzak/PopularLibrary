package ru.gb.popularlibrary.domain.repositories

import io.reactivex.rxjava3.core.Single
import ru.gb.popularlibrary.domain.entities.UserEntity

interface UsersRepository {
    // CRUD = Create/Read/Update/Delete
    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    fun getUsers():Single<List<UserEntity>>

}