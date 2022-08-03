package ru.gb.popularlibrary.ui.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gb.app
import ru.gb.popularlibrary.domain.entities.UserEntity
import ru.gb.popularlibrary.domain.repositories.UsersRepository
import ru.gb.popularlibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),UsersContract.View {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    //private val usersRepository: UsersRepository by lazy { app.usersRepository }
    private lateinit var usersPresenter:UsersContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showProgress(false)
        initViews()
        usersPresenter = UsersPresenter(app.usersRepository)
        usersPresenter.attach(this)
    }

    private fun initViews() {
        binding.reloadButton.setOnClickListener {
            usersPresenter.onRefresh()
        }
        initRecyclerView()
        showProgress(false)
    }

    private fun initRecyclerView() {
        binding.userReloadRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.userReloadRecyclerview.adapter = adapter
        //loadData()
    }

    override fun showUsers(users: List<UserEntity>) {
        adapter.setData(users)
    }

    override fun showError(throwable: Throwable) {

        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.userReloadRecyclerview.isVisible = !inProgress

    }

    override fun onDestroy() {
        usersPresenter.deAttach()
        super.onDestroy()
    }
}