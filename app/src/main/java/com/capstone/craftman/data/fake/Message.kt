package com.capstone.craftman.data.fake

data class Message(val text: String, val isFromMe: Boolean)

val chatMessages = listOf(
    Message("Halo", true),
    Message("Hai", false),
    Message("Berapa lama pengerjaan nya?", true),
    Message("3-5 hari ya kak", false),
    Message("Bisa lebih cepat?", true),
    Message("bisa kak, kami usahakan ya", false),
    Message("Ditunggu ya kak", true),
)