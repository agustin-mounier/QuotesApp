package com.example.quotes.presenters

import com.example.quotes.models.Quote
import com.example.quotes.mvp.BasePresenter
import com.example.quotes.views.QuotesDetailView

class QuotesDetailPresenter: BasePresenter<QuotesDetailView>() {

    fun initDetail(quote: Quote) {
        mView!!.setUp(quote)
    }
}