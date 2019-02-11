package com.altunagroup.AltunaGroup

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        displayScreen(-1)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun displayScreen(id: Int){
        val fragment = when (id) {
            R.id.nav_home -> {
                HomeFragment()
            }
            R.id.nav_payment -> {
                ChooseCustomerFragment.newInstance(1)
            }
            R.id.nav_inventory -> {
                ChooseCustomerFragment.newInstance(2)
            }
            R.id.nav_logout -> {
                HomeFragment()
            }
            else -> {
                HomeFragment()
            }
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.relativeLayoutHome, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        var intent : Intent


        preferencesHelper = PreferencesHelper(this)

        if (preferencesHelper.customerID == -1 && item.itemId != R.id.nav_home) {
            Snackbar.make(findViewById(R.id.relativeLayoutHome),"Primero elige un cliente de la lista",Snackbar.LENGTH_SHORT).show()
        } else {
            when (item.itemId) {
                R.id.nav_home -> {
                    toolbar.title = "Llaves Altuna de México"
                }
                R.id.nav_payment -> {
                    intent = Intent(this, CollectionListActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_inventory -> {
                    intent = Intent(this, ProductListActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_locksmith -> {
                    intent = Intent(this, LocksmithActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_logout -> {
                    preferencesHelper.loggedIn = false
                    intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else -> {
                    toolbar.title = "Llaves Altuna de México"
                }
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
