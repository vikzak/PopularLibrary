package ru.gb.popularlibrary.ui.users

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Observable
import ru.gb.popularlibrary.domain.entities.UserEntity

interface UsersContract {

    interface ViewModel {
        val usersLiveData: Observable<List<UserEntity>>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>
        val userDetailLiveData: Observable<Unit>

        fun onRefresh()
        fun onUserClick(userEntity: UserEntity)
    }

}