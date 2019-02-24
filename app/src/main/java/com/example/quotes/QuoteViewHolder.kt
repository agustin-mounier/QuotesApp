package com.example.quotes

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.quotes.models.Quote
import kotlinx.android.synthetic.main.quote_card_view.view.*

class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        const val nonAuthorText = "Anonymous"
    }

    private lateinit var quoteModel: Quote

    init {
        itemView.setOnClickListener {
            val detailIntent = Intent(it.context, QuotesDetailActivity::class.java)
            detailIntent.putExtra(QuotesFeedActivity.QUOTE_SELECTED_EXTRA, quoteModel)
            it.context.startActivity(detailIntent)
        }
    }

    fun bind(quote: Quote) {
        quoteModel = quote
        itemView.quote_card_text.text = quoteModel.quote

        val author = quoteModel.author
        if (author.isNullOrBlank()) {
            itemView.quote_card_author.text = nonAuthorText
        } else {
            itemView.quote_card_author.text = author
        }
    }
}