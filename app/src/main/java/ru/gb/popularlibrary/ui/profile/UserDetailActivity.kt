package ru.gb.popularlibrary.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gb.popularlibrary.R
import ru.gb.popularlibrary.databinding.ActivityMainBinding
import ru.gb.popularlibrary.databinding.ActivityUserDetailBinding
import ru.gb.popularlibrary.domain.entities.UserEntity
import ru.gb.popularlibrary.ui.users.UserViewHolder
import ru.gb.popularlibrary.ui.users.UsersAdapter
import kotlin.system.exitProcess

class UserDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailBinding
    //private lateinit var userEntity: UserEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.detailLoginTextView.text = userEntity.login.toString()

        binding.detailUserButtonClose.setOnClickListener {
            this.finish()
        }
    }
}