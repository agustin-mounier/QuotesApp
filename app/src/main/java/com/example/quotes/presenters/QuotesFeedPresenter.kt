package com.example.quotes.presenters

import com.example.quotes.FileUtils
import com.example.quotes.managers.QuotesManager
import com.example.quotes.models.Quote
import com.example.quotes.mvp.BasePresenter
import com.example.quotes.views.QuotesFeedView

class QuotesFeedPresenter: BasePresenter<QuotesFeedView>() {

    private val tags : MutableList<String> = mutableListOf()
    private var rand : String = "0"
    private lateinit var favoriteQuotes: MutableSet<Quote>

    fun fetchQuotes(page: Int = 0) {
        QuotesManager.getQuotes(rand = rand, offset = page * QuotesManager.quotesCount, tags = tags,
            successCallback = mView!!::showQuotes, errorCallback = mView!!::showError)
    }

    fun resetFeedWithTags(tags: List<String>) {
        this.tags.addAll(tags)
        mView!!.resetQuotesFeed()
        fetchQuotes()
    }

    fun explore() {
        rand = "t"
        tags.clear()
        mView!!.setToolbarTitle("Explore")
        mView!!.resetQuotesFeed()
        fetchQuotes()
    }

    fun favoriteQuotes() {
        mView!!.resetQuotesFeed(false)
        mView!!.showQuotes(favoriteQuotes.toList())
    }

    fun isFavorited(quote: Quote): Boolean {
        return favoriteQuotes.contains(quote)
    }

    fun addToFavorites(quote: Quote) {
        favoriteQuotes.add(quote)
    }

    fun removeFromFavorites(quote: Quote) {
        favoriteQuotes.remove(quote)
    }

    fun saveFavorites() {
        FileUtils.saveQuotes(mView!!.getContext(), favoriteQuotes)
    }

    fun loadFavorites() {
        favoriteQuotes = FileUtils.loadFavoriteQuotes(mView!!.getContext())
    }
}