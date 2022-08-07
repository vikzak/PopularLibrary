package ru.gb.popularlibrary.ui.users

import ru.gb.popularlibrary.domain.entities.UserEntity
import ru.gb.popularlibrary.domain.repositories.UsersRepository

class UsersPresenter(
    private val usersRepository: UsersRepository
) : UsersContract.Presenter {
    private var view: UsersContract.View? = null

    private var userList: List<UserEntity>? = null
    private var loadingUserListError: Throwable? = null
    private var inProgress: Boolean = false

    override fun attach(view: UsersContract.View) {
        this.view = view
        view.showProgress(inProgress)
        userList?.let { view.showUsers(it) }
        //loadingUserListError?.let { view.showError(it) }

    }

    override fun deAttach() {
        view = null
    }

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        view?.showProgress(true)
        inProgress = true
        usersRepository.getUsers(
            onSuccess = {
                view?.showProgress(false)
                view?.showUsers(it)
                inProgress = false
                //loadingUserListError = null
                userList = it
            },
            onError = {
                view?.showError(it)
                view?.showProgress(true)
                //loadingUserListError = it
                inProgress = false
            }
        )
    }
}