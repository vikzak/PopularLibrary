package ru.gb.popularlibrary.ui.users

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.gb.popularlibrary.domain.entities.UserEntity

interface UsersContract {

//    interface View {
//        fun showUsers(users: List<UserEntity>)
//        fun showError(throwable: Throwable)
//        fun showProgress(inProgress: Boolean)
//    }

    //    interface Presenter {
//        fun attach(view: View)
//        fun deAttach()
//        fun onRefresh()
//    }
    interface ViewModel {
        val usersLiveData: LiveData<List<UserEntity>>
        val errorLiveData: LiveData<Throwable>
        val progressLiveData: LiveData<Boolean>

        fun onRefresh()

//        fun <T> LiveData<T>.post(value: T) {
//            val mutable = this as? MutableLiveData<T>
//                ?: throw IllegalStateException("It is NOT MutableLiveData")
//            mutable.postValue(value)
//        }
    }

}