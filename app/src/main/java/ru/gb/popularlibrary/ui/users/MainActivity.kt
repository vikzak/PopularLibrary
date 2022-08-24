package ru.gb.popularlibrary.ui.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import ru.gb.app
import ru.gb.popularlibrary.domain.entities.UserEntity
import ru.gb.popularlibrary.databinding.ActivityMainBinding
import ru.gb.popularlibrary.ui.profile.UserDetailActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter {
        viewModel.onUserClick(it)
    }

    //private val usersRepository: UsersRepository by lazy { app.usersRepository }
    private lateinit var viewModel: UsersContract.ViewModel
    private var viewModelDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        initViewModel()
    }

    private fun initViewModel() {
        viewModel = extractViewModel()
        viewModelDisposable.addAll(
            viewModel.progressLiveData.subscribe { showProgress(it) },
            viewModel.errorLiveData.subscribe{ showError(it) },
            viewModel.usersLiveData.subscribe { showUsers(it) },
            viewModel.userDetailLiveData.subscribe { openDetailActivity() }
        )

    }

    private fun openDetailActivity() {
        startActivity(Intent(this, UserDetailActivity::class.java))
    }

    private fun extractViewModel(): UsersContract.ViewModel {
        return lastCustomNonConfigurationInstance as? UsersContract.ViewModel
            ?: UsersViewModel(app.usersRepository)
    }

    private fun initViews() {
        binding.reloadButton.setOnClickListener {
            viewModel.onRefresh()
        }
        initRecyclerView()
        showProgress(false)
        //clickToUserDetail()
    }

    private fun initRecyclerView() {
        binding.userReloadRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.userReloadRecyclerview.adapter = adapter
        //loadData()
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

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): UsersContract.ViewModel {
        return viewModel
    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
    }
}