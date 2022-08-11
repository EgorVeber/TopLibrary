package ru.gb.veber.toplibrary.core.navigator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.user.UsersFragment
import ru.gb.veber.toplibrary.usercreen.UserScreenFragment
import ru.gb.veber.toplibrary.usercreen.UserScreenFragment.Companion.KEY_USER

object UsersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UsersFragment.getInstance()
    }
}

data class UserScreen(private val user: GithubUser) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserScreenFragment.newInstance(Bundle().apply {
            putParcelable(KEY_USER, user)
        })
    }
}