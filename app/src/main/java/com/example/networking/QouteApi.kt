package com.example.networking

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {
    @GET("/quotes")
    suspend fun getQuotes() : Response<QuoteList>
    @GET("/todos")
    suspend fun getTodos(): Response<TodoResponse>

}