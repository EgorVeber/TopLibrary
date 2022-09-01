package ru.gb.veber.toplibrary.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.gb.veber.toplibrary.model.GithubUser
import ru.gb.veber.toplibrary.view.userdetails.UserDetailsFragment
import ru.gb.veber.toplibrary.view.userdetails.UserDetailsFragment.Companion.KEY_USER
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