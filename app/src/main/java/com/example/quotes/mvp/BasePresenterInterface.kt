package com.example.quotes.mvp

interface BasePresenterInterface<in V : BaseViewInterface> {

    fun attachView(view: V)

    fun detachView()
}