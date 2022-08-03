package ru.gb.popularlibrary.ui.users

import android.view.View
import ru.gb.popularlibrary.domain.entities.UserEntity

interface UsersContract {

    interface View {
        fun showUsers(users: List<UserEntity>)
        fun showError(throwable: Throwable)
        fun showProgress(inProgress: Boolean)
    }

    interface Presenter {
        fun attach(view: View)
        fun deAttach()
        fun onRefresh()
    }

}