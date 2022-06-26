package ru.gb.popularlibrary.data

import android.os.Looper
import android.os.Handler
import ru.gb.popularlibrary.domain.UserEntity
import ru.gb.popularlibrary.domain.UsersRepository


private const val DATA_LOADINGFAKE_DELAY = 1000L

class FakeUsersRepositoryImpl : UsersRepository {

    private val data: List<UserEntity> = listOf(
        UserEntity("mojombo", 1, "MDQ6VXNlcjE=","https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity("defunkt", 2, "MDQ6VXNlcjI=","https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntity("pjhyett", 3, "MDQ6VXNlcjM=","https://avatars.githubusercontent.com/u/3?v=4"),
        UserEntity("mojombo", 4, "MDQ6VXNlcjQ=","https://avatars.githubusercontent.com/u/4?v=4"),
        UserEntity("mojombo", 5, "MDQ6VXNlcjE=","https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity("defunkt", 6, "MDQ6VXNlcjI=","https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntity("pjhyett", 7, "MDQ6VXNlcjM=","https://avatars.githubusercontent.com/u/3?v=4"),
        UserEntity("mojombo", 8, "MDQ6VXNlcjQ=","https://avatars.githubusercontent.com/u/4?v=4"),
    )

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(data)
        },DATA_LOADINGFAKE_DELAY)
    }
}


