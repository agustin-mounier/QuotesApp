package com.example.quotes.managers

import android.annotation.SuppressLint
import com.example.quotes.models.Quote
import com.example.quotes.services.OpinionatedQuotesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


object QuotesManager : AbstractManager<OpinionatedQuotesService>() {

    val quotesCount = 10

    override val baseUrl: String
        get() = "https://opinionated-quotes-api.gigalixirapp.com/"

    override val service: OpinionatedQuotesService
        get() = retrofit.create(OpinionatedQuotesService::class.java)

    @SuppressLint("CheckResult")
    fun getQuotes(rand: String = "0", n: Int = quotesCount, offset: Int? = null, author: String? = null,
                  tags: List<String>? = null, tmode: String? = null, lang: String? = null,
                  successCallback: (result: List<Quote>) -> Unit, errorCallback: (error: String) -> Unit) {

        service.getQuotes(rand, n, offset, author, tags, tmode, lang)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> successCallback(result.quotes) },
                { error -> errorCallback(error.message!!) }
            )
    }
}