package ru.gb.popularlibrary.ui.users

import androidx.lifecycle.LiveData
import ru.gb.popularlibrary.domain.entities.UserEntity

interface UsersContract {

    interface ViewModel {
        val usersLiveData: LiveData<List<UserEntity>>
        val errorLiveData: LiveData<Throwable>
        val progressLiveData: LiveData<Boolean>
        val userDetailLiveData: LiveData<Unit>

        fun onRefresh()
        fun onUserClick(userEntity: UserEntity)
    }

}