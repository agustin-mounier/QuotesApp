package com.example.quotes.presenters

import com.example.quotes.models.Quote
import com.example.quotes.mvp.BasePresenter
import com.example.quotes.views.EditQuotesView

class EditQuotesPresenter: BasePresenter<EditQuotesView>() {

    fun initActivty(quote: Quote) {
        mView!!.setUp(quote)
    }
}