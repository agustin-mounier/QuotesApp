package com.example.quotes.mvp

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<in V : BaseViewInterface, T : BasePresenterInterface<V>>
    : AppCompatActivity(), BaseViewInterface {

    protected lateinit var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instantiatePresenter()
        mPresenter.attachView(this as V)
    }

    override fun getContext(): Context = this

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    abstract fun instantiatePresenter()
}