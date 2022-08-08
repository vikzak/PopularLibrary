package ru.gb.popularlibrary.domain.repositories

import ru.gb.popularlibrary.domain.entities.UserEntity

interface UsersRepository {
    // CRUD = Create/Read/Update/Delete
    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

}