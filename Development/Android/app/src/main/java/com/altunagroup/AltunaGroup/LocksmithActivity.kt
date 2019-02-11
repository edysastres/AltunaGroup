package com.altunagroup.AltunaGroup

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_locksmith.*

class LocksmithActivity : AppCompatActivity() {

    var fragment1: Fragment = LocksmithsFragment()
    var fragment2: Fragment = NewLocksmithFragment()
    var fm = supportFragmentManager
    var active = fragment1

    var locksmithName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locksmith)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        title = "Cerrajeros"

        fm.beginTransaction().add(R.id.relativeTabLocksmith, fragment2, "2").hide(fragment2).commit()
        fm.beginTransaction().add(R.id.relativeTabLocksmith,fragment1, "1").commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_locksmiths -> {
                title = "Cerrajeros"
                fm.beginTransaction().hide(active).show(fragment1).commit()
                active = fragment1
            }
            R.id.navigation_new_locksmith -> {
                title = "Nuevo Registro"
                /*
                if(locksmithName.isNotEmpty()) {
                    fm.beginTransaction().remove(fragment2).commit()
                    var params = Bundle()
                    params.putString("locksmithName", locksmithName)
                    fragment2 = NewLocksmithFragment()
                    fragment2.arguments = params
                    fm.beginTransaction().add(R.id.relativeTabLocksmith, fragment2, "2").commit()
                    fm.beginTransaction().hide(active).show(fragment2).commit()

                } else {
                    fm.beginTransaction().hide(active).show(fragment2).commit()
                }
                */
                fm.beginTransaction().remove(fragment2).commit()
                var params = Bundle()
                params.putString("locksmithName", locksmithName)
                fragment2 = NewLocksmithFragment()
                fragment2.arguments = params
                fm.beginTransaction().add(R.id.relativeTabLocksmith, fragment2, "2").commit()
                fm.beginTransaction().hide(active).show(fragment2).commit()

                active = fragment2
            }
            else -> {
                title = "Cerrajeros"
                fm.beginTransaction().hide(active).show(fragment1).commit()
                active = fragment1
            }
        }

        return@OnNavigationItemSelectedListener true
    }

    fun getNavigation() : BottomNavigationView {
        return navigation
    }

    fun setLocksmithInfo(name: String){
        locksmithName = name
    }
}
