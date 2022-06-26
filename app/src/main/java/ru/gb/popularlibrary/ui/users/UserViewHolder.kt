package ru.gb.popularlibrary.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.gb.popularlibrary.R
import ru.gb.popularlibrary.domain.entities.UserEntity
import ru.gb.popularlibrary.databinding.ItemUserBinding

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private val binding = ItemUserBinding.bind(itemView)
    fun bind(userEntity: UserEntity) {
        binding.awatarImageView.load(userEntity.avatarUrl)
        binding.loginTextView.text = "login: " + userEntity.login
        binding.userIdTextView.text = "iser ID: " + userEntity.id.toString()
    }
}