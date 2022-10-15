package ru.gb.popularlibrary.ui.users

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.gb.app
import ru.gb.popularlibrary.databinding.ActivityMainBinding
import ru.gb.popularlibrary.domain.entities.UserEntity
import ru.gb.popularlibrary.ui.profile.UserDetailActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter {
        viewModel.onUserClick(it)
    }

    private val viewModel: UsersViewModel by lazy { UsersViewModel(app.applicationComponent.getUsersRepository()) }
    private val viewModelDisposable = CompositeDisposable()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initViewModel()
    }

    private fun initViewModel() {
        viewModelDisposable.addAll(
            viewModel.progressLiveData.subscribe { showProgress(it) },
            viewModel.errorLiveData.subscribe { showError(it) },
            viewModel.usersLiveData.subscribe { showUsers(it) },
            viewModel.userDetailLiveData.subscribe { openDetailActivity() }
        )
    }

    private fun openDetailActivity() {
        startActivity(Intent(this, UserDetailActivity::class.java))
    }


    private fun initViews() {
        binding.reloadButton.setOnClickListener {
            viewModel.onRefresh()
        }
        initRecyclerView()
        showProgress(false)
    }

    private fun initRecyclerView() {
        binding.userReloadRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.userReloadRecyclerview.adapter = adapter
    }

    private fun showUsers(users: List<UserEntity>) {
        adapter.setData(users)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.userReloadRecyclerview.isVisible = !inProgress

    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
    }
}