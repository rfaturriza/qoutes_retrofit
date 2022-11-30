package com.example.networking

data class TodoResponse(
    val message: String,
    val status: String,
    val data: List<Data>
)