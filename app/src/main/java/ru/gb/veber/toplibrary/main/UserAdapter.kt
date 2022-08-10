package ru.gb.veber.toplibrary.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.gb.veber.toplibrary.R
import ru.gb.veber.toplibrary.model.GithubUser

class UserAdapter() : RecyclerView.Adapter<GithubUserViewHolder>() {

    var users: List<GithubUser> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        //ItemUserBinding.inflate(LayoutInflater.from(parent.context))
        return GithubUserViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size
}

class GithubUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //val binding = ItemUserBinding.inflate(LayoutInflater.from(itemView.context))

    private val tvLogin by lazy { itemView.findViewById<TextView>(R.id.tvUserLogin) }
    //при обращзении будет вызыватся иициализировнная view

    fun bind(item: GithubUser) = with(item) {
        tvLogin.text = login
    }
}
