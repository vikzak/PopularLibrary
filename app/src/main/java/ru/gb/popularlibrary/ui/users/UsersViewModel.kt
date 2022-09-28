package ru.gb.popularlibrary.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import ru.gb.popularlibrary.domain.entities.UserEntity
import ru.gb.popularlibrary.domain.repositories.UsersRepository
import java.lang.IllegalStateException

class UsersViewModel(
    private val usersRepository: UsersRepository
) : UsersContract.ViewModel, ViewModel() {

    override val usersLiveData: Observable<List<UserEntity>> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()
    override val userDetailLiveData: Observable<Unit> = BehaviorSubject.create()

    override fun onRefresh() {
        loadData()
    }

    override fun onUserClick(userEntity: UserEntity) {
        userDetailLiveData
    }

    private fun loadData() {
        progressLiveData.mutable().onNext(true)

        usersRepository.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
            onSuccess = {
                progressLiveData.mutable().onNext(false)
                usersLiveData.mutable().onNext(it)
            },
            onError = {
                progressLiveData.mutable().onNext(false)
                errorLiveData.mutable().onNext(it)
            }
        )
    }

    private fun <T> LiveData<T>.mutable():MutableLiveData<T>{
       return this as? MutableLiveData<T> ?: throw IllegalStateException("It is NOT MutableLiveData")
    }
    private fun <T : Any> Observable<T>.mutable():Subject<T>{
        return this as? Subject<T> ?: throw IllegalStateException("It is NOT MutableLiveData")
    }
}