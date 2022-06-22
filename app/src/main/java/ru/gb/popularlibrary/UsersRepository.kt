package ru.gb.popularlibrary

interface UsersRepository {
    // CRUD = Create/Read/Update/Delete
    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

}