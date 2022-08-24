package ru.gb.popularlibrary.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gb.popularlibrary.R
import ru.gb.popularlibrary.databinding.ActivityMainBinding
import ru.gb.popularlibrary.databinding.ActivityUserDetailBinding
import kotlin.system.exitProcess

class UserDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailUserButtonClose.setOnClickListener {
            this.finish()
        }
    }
}