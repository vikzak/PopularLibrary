package ru.gb.popularlibrary.ui.users

import ru.gb.popularlibrary.domain.repositories.UsersRepository

class UsersPresenter(
    private val usersRepository: UsersRepository
) : UsersContract.Presenter {
    private var view: UsersContract.View? = null

    override fun attach(view: UsersContract.View) {
        this.view = view
    }

    override fun deAttach() {
        view = null
    }

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        view?.showProgress(true)
        usersRepository.getUsers(
            onSuccess = {
                view?.showProgress(false)
                view?.showUsers(it)
            },
            onError = {
                view?.showError(it)
                view?.showProgress(true)
            }
        )
    }
}