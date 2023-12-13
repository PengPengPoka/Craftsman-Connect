package com.capstone.craftman.data.fake

data class Message(val text: String, val isFromMe: Boolean)

val chatMessages = listOf(
    Message("Halo", true),
    Message("Hai", false)
)