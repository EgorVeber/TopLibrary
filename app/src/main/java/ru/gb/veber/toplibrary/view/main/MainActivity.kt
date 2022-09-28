package ru.gb.veber.toplibrary.view.main

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.gb.veber.toplibrary.R
import ru.gb.veber.toplibrary.core.App
import ru.gb.veber.toplibrary.databinding.ActivityMainBinding
import ru.gb.veber.toplibrary.presenter.MainPresenter

class MainActivity : MvpAppCompatActivity(), MainView{

    private lateinit var binding: ActivityMainBinding
    private val navigator = AppNavigator(this, R.id.containerMain)

    private val presenter by moxyPresenter { MainPresenter(App.instance.router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





        //      var db = App.instance.database.userDao()
//
////        Log.d("DBTEST", "it.toString()")
//        db.queryForAllUsers().subscribeOn(Schedulers.io()).subscribe({
//            Log.d("DBTEST", it.size.toString())
//            it.forEach {
//                if(it.id==4||it.id==3||it.id==1){
//                    Log.d("DBTEST", it.toString())
//                }
//            }
//        },{
//            Log.d("DBTEST", "errror")
//            Log.d("DBTEST", it.localizedMessage)
//        })
//
//        db.queryForAllRepos().subscribeOn(Schedulers.io()).subscribe({
//            Log.d("DBTEST", it.size.toString())
//            it.sortedBy { it.userId }.forEach {
//                Log.d("DBTEST", it.name + " "+it.userId)
//            }
//        }, {
//            Log.d("DBTEST", "errror")
//            Log.d("DBTEST", it.localizedMessage)
//        })
//
//
//        db.deleteAllRepos().subscribeOn(Schedulers.io()).subscribe({
//            Log.d("DBTEST", "success")
//
//        }, {
//            Log.d("DBTEST", "errror")
//            Log.d("DBTEST", it.localizedMessage)
//        })
//
//        db.insertAllRepos(listOf(RepoDBObject(2, 1, "2", 4), RepoDBObject(3, 1, "2", 5)))
//            .subscribeOn(Schedulers.io()).subscribe({
//                Log.d("DBTEST", "success")
//            },{
//                Log.d("DBTEST", "errror")
//                Log.d("DBTEST", it.localizedMessage)
//            })

//       db.queryForTest().subscribeOn(Schedulers.io()).subscribe({
//           Log.d("DBTEST", "Success")
//       },{
//          Log.d("DBTEST", "errror")
//          Log.d("DBTEST", it.localizedMessage)
//       })

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        App.instance.navigationHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.fragments.forEach { fragment ->
            if (fragment is BackPressedListener && fragment.onBackPressed()) {
                return
            }
        }
        presenter.onBackPressed()
    }

}