package ru.gb.popularlibrary.domain

import ru.gb.popularlibrary.domain.UserEntity

interface UsersRepository {
    // CRUD = Create/Read/Update/Delete
    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

}