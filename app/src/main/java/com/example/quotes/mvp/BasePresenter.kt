package com.example.quotes.mvp

open class BasePresenter<V : BaseViewInterface> : BasePresenterInterface<V> {

    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }
}