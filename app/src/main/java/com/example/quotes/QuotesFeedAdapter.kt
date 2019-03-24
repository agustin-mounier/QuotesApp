package com.example.quotes

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.quotes.models.Quote
import com.example.quotes.presenters.QuotesFeedPresenter

class QuotesFeedAdapter(private val presenter: QuotesFeedPresenter): RecyclerView.Adapter<QuoteViewHolder>() {

    private val quotes: MutableList<Quote> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_card_view, parent, false)
        return QuoteViewHolder(view, presenter)
    }

    override fun getItemCount(): Int {
        return quotes.size
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(quotes[position])
    }

    fun addQuotes(quotesToAdd: List<Quote>) {
        quotes.addAll(quotesToAdd)
    }

    fun clearQuotes() {
        quotes.clear()
    }
}