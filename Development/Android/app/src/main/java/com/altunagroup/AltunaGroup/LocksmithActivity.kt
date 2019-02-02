package com.altunagroup.AltunaGroup

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_locksmith.*

class LocksmithActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        val fragment = when (item.itemId) {
            R.id.navigation_locksmiths -> {
                LocksmithsFragment()
            }
            R.id.navigation_new_locksmith -> {
                NewLocksmithFragment()
            }
            else -> {
                LocksmithsFragment()
            }
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.relativeTabLocksmith, fragment)
            .commit()

        return@OnNavigationItemSelectedListener true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locksmith)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        title = "Cerrajeros"

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.relativeTabLocksmith, LocksmithsFragment())
            .commit()
    }
}
