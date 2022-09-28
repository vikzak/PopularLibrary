package ru.gb.popularlibrary.data.retrofit


import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import ru.gb.popularlibrary.domain.entities.UserEntity
import ru.gb.popularlibrary.domain.repositories.UsersRepository


private const val DATA_LOADINGFAKE_DELAY = 1000L

class RetrofitUsersRepositoryImpl(
    private val apiGithub: GithubApi
) : UsersRepository {


    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {

        apiGithub.getUsers().subscribeBy(
                onSuccess = {
                    users -> onSuccess.invoke(users.map { it.toUserEntity() })
                },
                onError = {
                    onError?.invoke(it)
                }
            )
    }



    override fun getUsers(): Single<List<UserEntity>> = apiGithub.getUsers()
        .map { users ->
            users.map {
                it.toUserEntity()
            }
        }


}




