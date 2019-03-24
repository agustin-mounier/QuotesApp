package com.example.quotes

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.quotes.models.Quote
import com.example.quotes.presenters.QuotesFeedPresenter
import kotlinx.android.synthetic.main.quote_card_view.view.*

class QuoteViewHolder(itemView: View, presenter: QuotesFeedPresenter) : RecyclerView.ViewHolder(itemView) {

    companion object {
        const val nonAuthorText = "Anonymous"
    }

    private lateinit var quoteModel: Quote

    init {
        itemView.setOnClickListener {
            val editIntent = Intent(it.context, EditQuotesActivity::class.java)
            editIntent.putExtra(QuotesFeedActivity.QUOTE_SELECTED_EXTRA, quoteModel)
            it.context.startActivity(editIntent)
        }
        itemView.quote_favorite_button.setOnClickListener {
            if (presenter.isFavorited(quoteModel)) {
                presenter.removeFromFavorites(quoteModel)
            } else {
                presenter.addToFavorites(quoteModel)
            }
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

        val flexboxLayout = itemView.quote_tags_container
        flexboxLayout.removeAllViews()
        for (tag in quote.tags) {
            val tagView = LayoutInflater.from(itemView.context).inflate(R.layout.tag_layout, null) as TextView
            tagView.text = tag
            flexboxLayout.addView(tagView)
        }
    }
}