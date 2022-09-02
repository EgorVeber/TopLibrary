package ru.gb.veber.toplibrary.view.userrepository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.gb.veber.toplibrary.core.App
import ru.gb.veber.toplibrary.databinding.FragmentRepoUserBinding
import ru.gb.veber.toplibrary.model.data.ReposDto
import ru.gb.veber.toplibrary.presenter.RepoUserPresenter
import ru.gb.veber.toplibrary.view.main.BackPressedListener

class RepoUserFragment : MvpAppCompatFragment(), RepoUserView, BackPressedListener {

    private val presenter: RepoUserPresenter by moxyPresenter {
        RepoUserPresenter(App.instance.router, arguments?.getParcelable(KEY_REPO))
    }

    private lateinit var binding: FragmentRepoUserBinding

    companion object {
        const val KEY_REPO = "KEY_REPO"
        fun getInstance(bundle: Bundle) = RepoUserFragment().apply { arguments = bundle }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentRepoUserBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }


    override fun showRepo(repo: ReposDto) = with(binding) {
        repoId.text = repo.id.toString()
        nodeId.text = repo.nodeId
        name.text = repo.name
        description.text = repo.description
        createdAt.text = repo.createdAt
        updatedAt.text = repo.updatedAt
        language.text = repo.language
        forksCount.text = repo.forksCount.toString()
    }

    override fun onBackPressed() = presenter.onBackPressed()
}