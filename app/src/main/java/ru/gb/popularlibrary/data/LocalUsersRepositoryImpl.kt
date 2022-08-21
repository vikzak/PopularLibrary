package ru.gb.popularlibrary.data

import android.os.Looper
import android.os.Handler
import ru.gb.popularlibrary.domain.entities.UserEntity
import ru.gb.popularlibrary.domain.repositories.UsersRepository


private const val DATA_LOADINGFAKE_DELAY = 1000L

class LocalUsersRepositoryImpl : UsersRepository {

    private val data: List<UserEntity> = listOf(
        UserEntity("https://avatars.githubusercontent.com/u/1?v=4", 1, "mojombo", "MDQ6VXNlcjE="),
        UserEntity("https://avatars.githubusercontent.com/u/2?v=4", 2, "defunkt", "MDQ6VXNlcjI="),
        UserEntity("https://avatars.githubusercontent.com/u/3?v=4", 3, "pjhyett", "MDQ6VXNlcjM="),
        UserEntity("https://avatars.githubusercontent.com/u/4?v=4", 4, "wycats", "MDQ6VXNlcjQ="),

        )

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(data)
            onError?.invoke(IllegalStateException("I'm Error"))
        }, DATA_LOADINGFAKE_DELAY)
    }
}


