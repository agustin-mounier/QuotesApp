package com.example.quotes.presenters

import com.example.quotes.managers.QuotesManager
import com.example.quotes.mvp.BasePresenter
import com.example.quotes.views.QuotesFeedView

class QuotesFeedPresenter: BasePresenter<QuotesFeedView>() {

    private val tags : MutableList<String> = mutableListOf()
    private var rand : String = "0"

    fun fetchQuotes(page: Int = 0) {
        QuotesManager.getQuotes(rand = rand, offset = page * QuotesManager.quotesCount, tags = tags,
            successCallback = mView!!::showQuotes, errorCallback = mView!!::showError)
    }

    fun resetFeedWithTags(tags: List<String>) {
        this.tags.addAll(tags)
        mView!!.resetQuotesFeed()
    }

    fun explore() {
        rand = "t"
        tags.clear()
        mView!!.setToolbarTitle("Explore")
        mView!!.resetQuotesFeed()
    }
}