package ru.gb.veber.toplibrary.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.gb.veber.toplibrary.R
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.user.ItemClickListener

class UserAdapter() : RecyclerView.Adapter<GithubUserViewHolder>() {


    private lateinit var userClick: ItemClickListener

    fun setOnUserClickListener(listener: ItemClickListener) {
        userClick = listener
    }


    var users: List<GithubUser> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        return GithubUserViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false), userClick)
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size
}

class GithubUserViewHolder(itemView: View, private val userClick: ItemClickListener) :
    RecyclerView.ViewHolder(itemView) {
    private val tvLogin by lazy { itemView.findViewById<TextView>(R.id.tvUserLogin) }
    fun bind(item: GithubUser) = with(item) {
        tvLogin.text = login
        itemView.setOnClickListener {
            Log.d("TAG", "bind() called")
            userClick.onUserClick(item)
        }
    }
}
