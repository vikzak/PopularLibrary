package ru.gb.popularlibrary.data

import android.os.Looper
import android.os.Handler
import io.reactivex.rxjava3.core.Single
import ru.gb.popularlibrary.domain.entities.UserEntity
import ru.gb.popularlibrary.domain.repositories.UsersRepository


private const val DATA_LOADING_LOCALREPOSITORY_DELAY = 1000L

class LocalUsersRepositoryImpl : UsersRepository {

    private val data: List<UserEntity> = listOf(
        UserEntity("https://avatars.githubusercontent.com/u/1?v=4", "https://api.github.com/users/mojombo/followers", 1,"mojombo", "MDQ6VXNlcjE="),
        UserEntity("https://avatars.githubusercontent.com/u/2?v=4", "https://api.github.com/users/mojombo/followers", 2,"defunkt", "MDQ6VXNlcjI="),
        UserEntity("https://avatars.githubusercontent.com/u/3?v=4", "https://api.github.com/users/mojombo/followers", 3,"pjhyett", "MDQ6VXNlcjM="),
        UserEntity("https://avatars.githubusercontent.com/u/4?v=4", "https://api.github.com/users/mojombo/followers", 4,"wycats", "MDQ6VXNlcjQ="),
        UserEntity("https://avatars.githubusercontent.com/u/4?v=4", "https://api.github.com/users/mojombo/followers", 5,"wycats", "MDQ6VXNlcjQ="),
        UserEntity("https://avatars.githubusercontent.com/u/4?v=4", "https://api.github.com/users/mojombo/followers", 6,"wycats", "MDQ6VXNlcjQ=")
        )

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(data)
            onError?.invoke(IllegalStateException("I'm Error"))
        }, DATA_LOADING_LOCALREPOSITORY_DELAY)
    }

    override fun getUsers(): Single<List<UserEntity>> = Single.just(data)
}


