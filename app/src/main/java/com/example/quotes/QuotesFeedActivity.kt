package com.example.quotes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.Toast
import com.example.quotes.models.Quote
import com.example.quotes.mvp.BaseActivity
import com.example.quotes.presenters.QuotesFeedPresenter
import com.example.quotes.views.QuotesFeedView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.ArrayList
import kotlin.collections.HashSet
import kotlin.collections.List
import kotlin.collections.forEach
import kotlin.collections.toList


class QuotesFeedActivity : BaseActivity<QuotesFeedView, QuotesFeedPresenter>(),
    NavigationView.OnNavigationItemSelectedListener, QuotesFeedView {

    companion object {
        const val QUOTE_SELECTED_EXTRA = "quote_selected_extra"
        private const val TAG_SELECTION_RC: Int = 1
    }

    private lateinit var scrollListener: InfiniteScrollViewListener
    private var tags = HashSet<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initNavigationDrawer()
        initFeed()
    }

    override fun createPresenter(): QuotesFeedPresenter {
        return QuotesFeedPresenter()
    }

    override fun showQuotes(quotes: List<Quote>) {
        val quotesFeedAdapter = quotes_feed.adapter as QuotesFeedAdapter
        quotesFeedAdapter.addQuotes(quotes)
        quotesFeedAdapter.notifyDataSetChanged()
        quotes.forEach { tags.addAll(it.tags) }
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun resetQuotesFeed(infiniteScrollEnabled: Boolean) {
        scrollListener.resetState()
        scrollListener.setEnable(infiniteScrollEnabled)
        (quotes_feed.adapter as QuotesFeedAdapter).clearQuotes()
    }

    override fun setToolbarTitle(title: String) {
        toolbar.title = title
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == TAG_SELECTION_RC) {
            if (resultCode == Activity.RESULT_OK) {
                val tags = data!!.getStringArrayListExtra(TagSelectionActivity.TAGS_EXTRA)
                mPresenter.resetFeedWithTags(tags)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.explore -> {
                mPresenter.explore()
            }
            R.id.tags -> {
                val intent = Intent(this, TagSelectionActivity::class.java)
                intent.putStringArrayListExtra("TAG_LIST", tags.toList() as ArrayList<String>?)
                startActivityForResult(intent, TAG_SELECTION_RC)
            }
            R.id.favorites -> {
                mPresenter.favoriteQuotes()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onStop() {
        mPresenter.saveFavorites()
        super.onStop()
    }

    private fun initNavigationDrawer() {
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun initFeed() {
        val layoutManager = LinearLayoutManager(this)
        scrollListener = InfiniteScrollViewListener(layoutManager, mPresenter::fetchQuotes)

        quotes_feed.layoutManager = layoutManager
        quotes_feed.addOnScrollListener(scrollListener)
        quotes_feed.adapter = QuotesFeedAdapter(mPresenter)
        mPresenter.loadFavorites()
        mPresenter.fetchQuotes()
    }
}
