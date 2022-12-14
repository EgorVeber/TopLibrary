package ru.gb.veber.toplibrary.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.gb.veber.toplibrary.model.network.ReposDto
import ru.gb.veber.toplibrary.view.userdetails.UserDetailsFragment
import ru.gb.veber.toplibrary.view.userrepository.RepoUserFragment
import ru.gb.veber.toplibrary.view.userrepository.RepoUserFragment.Companion.KEY_REPO
import ru.gb.veber.toplibrary.view.users.UsersFragment

object UsersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UsersFragment.getInstance()
    }
}

data class UserScreen(private val userLogin: String) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserDetailsFragment.newInstance(userLogin)
    }
}

data class RepoScreen(private val repo: ReposDto) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return RepoUserFragment.getInstance(Bundle().apply {
            putParcelable(KEY_REPO,repo)
        })
    }
}
