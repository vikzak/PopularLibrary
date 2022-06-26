package ru.gb.popularlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gb.popularlibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    private val usersRepository:UsersRepository = FakeUsersRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.reloadButton.setOnClickListener {
            initRecyclerView()
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.userReloadRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.userReloadRecyclerview.adapter = adapter
        usersRepository.getUsers (
            onSuccess = adapter::setData,
            onError = {
                Toast.makeText(this, "Error load data", Toast.LENGTH_SHORT).show()
            }
        )

    }
}