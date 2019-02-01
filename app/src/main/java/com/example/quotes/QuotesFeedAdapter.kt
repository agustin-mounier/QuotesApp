package com.example.quotes

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.quotes.models.Quote

class QuotesFeedAdapter: RecyclerView.Adapter<QuoteViewHolder>() {

    private var quotes: MutableList<Quote> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_card_view, parent)
        return QuoteViewHolder(view)
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
}