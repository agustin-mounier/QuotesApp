package com.example.quotes.mvp

open class BasePresenter<V : BaseViewInterface> {

    protected var mView: V? = null

    fun attachView(view: V) {
        mView = view
    }

    fun detachView() {
        mView = null
    }
}