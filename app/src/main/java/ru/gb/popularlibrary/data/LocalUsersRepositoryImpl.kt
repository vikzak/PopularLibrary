package ru.gb.popularlibrary.data

import android.os.Handler
import io.reactivex.rxjava3.core.Single
import ru.gb.popularlibrary.data.retrofit.UserEntityDto
import ru.gb.popularlibrary.domain.entities.UserEntity
import ru.gb.popularlibrary.domain.repositories.UsersRepository


private const val DATA_LOADING_LOCALREPOSITORY_DELAY = 1000L

class LocalUsersRepositoryImpl(
    private val uiHandler: Handler
) : UsersRepository {

    private val data: List<UserEntity> = listOf(
        UserEntity("https://avatars.githubusercontent.com/u/1?v=4","mojombo",1,"https://api.github.com/users/mojombo/followers", ),
        UserEntity("https://avatars.githubusercontent.com/u/2?v=4","defunkt",2,"https://api.github.com/users/mojombo/followers"),
        UserEntity("https://avatars.githubusercontent.com/u/3?v=4","pjhyett",3,"https://api.github.com/users/mojombo/followers"),
        UserEntity("https://avatars.githubusercontent.com/u/4?v=4", "wycats",4,"https://api.github.com/users/mojombo/followers"),
        UserEntity("https://avatars.githubusercontent.com/u/4?v=4", "wycats", 5,"https://api.github.com/users/mojombo/followers"),
        UserEntity("https://avatars.githubusercontent.com/u/4?v=4", "wycats",6,"https://api.github.com/users/mojombo/followers")
        )

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        uiHandler.postDelayed({
            onSuccess(data)
            onError?.invoke(IllegalStateException("I'm Error"))
        }, DATA_LOADING_LOCALREPOSITORY_DELAY)
    }

    override fun getUsers(): Single<List<UserEntity>> = Single.just(data)
}


