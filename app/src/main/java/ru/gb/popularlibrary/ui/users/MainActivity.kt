package ru.gb.popularlibrary.ui.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gb.app
import ru.gb.popularlibrary.domain.entities.UserEntity
import ru.gb.popularlibrary.domain.repositories.UsersRepository
import ru.gb.popularlibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    private val usersRepository: UsersRepository by lazy { app.usersRepository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showProgress(false)
        initViews()
    }

    private fun initViews() {
        binding.reloadButton.setOnClickListener {
            initRecyclerView()
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.userReloadRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.userReloadRecyclerview.adapter = adapter
        loadData()
    }

    private fun loadData() {
        showProgress(true)
        usersRepository.getUsers(
            onSuccess = {
                showProgress(false)
                onDataLoader(it)
            },
            onError = {
                onError(it)
                showProgress(true)
            }
        )
    }

    private fun onDataLoader(it: List<UserEntity>) {
        adapter.setData(it)
    }

    private fun onError(throwable: Throwable) {

        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.userReloadRecyclerview.isVisible = !inProgress

    }
}