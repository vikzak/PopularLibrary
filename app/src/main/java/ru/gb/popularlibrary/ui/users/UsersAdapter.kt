package ru.gb.popularlibrary.ui.users

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.popularlibrary.domain.entities.UserEntity

class UsersAdapter : RecyclerView.Adapter<UserViewHolder>() {
    private val data = mutableListOf<UserEntity>()

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(position: Int): UserEntity {
        return data[position]
    }

    @SuppressLint("notifyDataSetChanged")
    fun setData(users: List<UserEntity>) {
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()

    }

}
