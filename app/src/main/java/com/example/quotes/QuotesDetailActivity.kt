package com.example.quotes

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.example.quotes.models.Quote
import com.example.quotes.mvp.BaseActivity
import com.example.quotes.presenters.QuotesDetailPresenter
import com.example.quotes.views.QuotesDetailView
import kotlinx.android.synthetic.main.quotes_detail_layout.*

class QuotesDetailActivity : BaseActivity<QuotesDetailView, QuotesDetailPresenter>(), QuotesDetailView {

    companion object {
        const val EDIT_QUOTE_EXTRA = "edit_quote_extra"
    }

    private lateinit var selectedQuote: Quote

    override fun instantiatePresenter() {
        mPresenter = QuotesDetailPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quotes_detail_layout)
        selectedQuote = intent.getParcelableExtra(QuotesFeedActivity.QUOTE_SELECTED_EXTRA)
        mPresenter.initDetail(selectedQuote)
    }

    override fun setUp(selectedQuote: Quote) {
        quotes_detail_text.text = selectedQuote.quote
        quotes_detail_author.text = selectedQuote.author
        for (tag in selectedQuote.tags) {
            val textView = TextView(this)
            textView.text = tag
            quotes_detail_tags.addView(textView)
        }

        edit_quote_button.setOnClickListener {
            val detailIntent = Intent(this, EditQuotesActivity::class.java)
            detailIntent.putExtra(EDIT_QUOTE_EXTRA, selectedQuote)
            startActivity(detailIntent)
        }
    }
}