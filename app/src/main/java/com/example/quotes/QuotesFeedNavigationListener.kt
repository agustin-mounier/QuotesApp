package com.example.quotes

import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import com.example.quotes.presenters.QuotesFeedPresenter

class QuotesFeedNavigationListener(private val presenter: QuotesFeedPresenter, private val drawerLayout: DrawerLayout) :
    NavigationView.OnNavigationItemSelectedListener {


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.explore -> {
                presenter.explore()
            }
            R.id.tags -> {

            }
            R.id.favorites -> {

            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}