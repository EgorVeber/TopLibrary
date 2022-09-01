package ru.gb.veber.toplibrary.view.userdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.veber.toplibrary.databinding.ItemReposBinding
import ru.gb.veber.toplibrary.databinding.ItemUserBinding
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.model.GithubUserRepos
import ru.gb.veber.toplibrary.network.ReposDto
import ru.gb.veber.toplibrary.utils.loadGlide


typealias OnUserClickListener = (login: String) -> Unit

class ReposAdapter(
    private val onUserClickListener: OnUserClickListener,
) : RecyclerView.Adapter<GithubUserReposViewHolder>() {

    var repos: List<ReposDto> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserReposViewHolder {
        return GithubUserReposViewHolder(ItemReposBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false),
            onUserClickListener)
    }

    override fun onBindViewHolder(holder: GithubUserReposViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    override fun getItemCount() = repos.size
}

class GithubUserReposViewHolder(
    private val binding: ItemReposBinding,
    private val onUserClickListener: OnUserClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ReposDto) = with(binding) {

        nameRepo.text = item.name

        dateCreating.text=item.createdAt

        root.setOnClickListener {
           // onUserClickListener.invoke(item.login)
        }
    }
}
