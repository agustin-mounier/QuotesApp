package com.example.quotes

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.quotes.models.Quote
import kotlinx.android.synthetic.main.quote_card_view.view.*

class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nonAuthorText = "Anonymous"

    fun bind(quote: Quote) {
        itemView.quote_card_text.text = quote.quote

        val author = quote.author
        if (author.isNullOrBlank()) {
            itemView.quote_card_author.text = nonAuthorText
        } else {
            itemView.quote_card_author.text = author
        }
    }
}