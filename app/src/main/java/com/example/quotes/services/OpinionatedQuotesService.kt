package com.example.quotes.services

import com.example.quotes.models.QuotesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OpinionatedQuotesService {

    @GET("v1/quotes")
    fun getQuotes(
        @Query("rand") rand: String = "0", @Query("n") n: Int, @Query("offset") offset: Int?,
        @Query("author") author: String?, @Query("tags") tags: List<String>?,
        @Query("tmode") tmode: String?, @Query("lang") lang: String?
    ): Observable<QuotesResponse>
}