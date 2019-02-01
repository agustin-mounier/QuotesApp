package com.example.quotes.presenters

import com.example.quotes.managers.QuotesManager
import com.example.quotes.mvp.BasePresenter
import com.example.quotes.views.QuotesFeedView

class QuotesFeedPresenter: BasePresenter<QuotesFeedView>() {

    fun fetchQuotes() {
        QuotesManager.getQuotes(successCallback = mView!!::showQuotes, errorCallback = mView!!::showError)
    }
}