package ru.gb.popularlibrary.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.gb.popularlibrary.domain.entities.UserEntity
import ru.gb.popularlibrary.domain.repositories.UsersRepository
import ru.gb.popularlibrary.utils.SingleEventsLiveData
import java.lang.IllegalStateException

class UsersViewModel(
    private val usersRepository: UsersRepository
) : UsersContract.ViewModel {

// было
//    private val _usersLiveData = MutableLiveData<List<UserEntity>>()
//    override val usersLiveData: LiveData<List<UserEntity>>
//        get() = _usersLiveData
// стало:
    override val usersLiveData: LiveData<List<UserEntity>> = MutableLiveData()
    override val errorLiveData: LiveData<Throwable> = SingleEventsLiveData()
    override val progressLiveData: LiveData<Boolean> = MutableLiveData()

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        progressLiveData.mutable().postValue(true)
        usersRepository.getUsers(
            onSuccess = {
                progressLiveData.mutable().postValue(false)
                usersLiveData.mutable().postValue(it)
            },
            onError = {
                progressLiveData.mutable().postValue(false)
                errorLiveData.mutable().postValue(it)
            }
        )
    }

    private fun <T> LiveData<T>.mutable():MutableLiveData<T>{
       return this as? MutableLiveData<T> ?: throw IllegalStateException("It is NOT MutableLiveData")
    }
}