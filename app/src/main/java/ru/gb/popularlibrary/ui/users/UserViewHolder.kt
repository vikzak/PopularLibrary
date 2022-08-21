package ru.gb.popularlibrary.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.gb.popularlibrary.R
import ru.gb.popularlibrary.domain.entities.UserEntity
import ru.gb.popularlibrary.databinding.ItemUserBinding

class UserViewHolder(
    parent: ViewGroup,
    private val inItemClickListener: (userEntity: UserEntity) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private lateinit var userEntity: UserEntity

    private val binding = ItemUserBinding.bind(itemView).apply {
        root.setOnClickListener {
            inItemClickListener.invoke(userEntity)
            Toast.makeText(itemView.context, userEntity.login, Toast.LENGTH_SHORT).show()

        }
    }

    fun bind(userEntity: UserEntity) {
        this.userEntity = userEntity
        binding.awatarImageView.load(userEntity.avatarUrl)
        binding.loginTextView.text = "login: " + userEntity.login
        binding.userIdTextView.text = "user ID: " + userEntity.id.toString()
    }
}