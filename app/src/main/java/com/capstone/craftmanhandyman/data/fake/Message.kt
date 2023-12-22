package com.capstone.craftmanhandyman.data.fake

data class Message(val text: String, val isFromMe: Boolean)

val chatMessages = listOf(
    Message("Halo", false),
    Message("Hai", true),
    Message("Berapa lama pengerjaan nya?", false),
    Message("3-5 hari ya kak", true),
    Message("Bisa lebih cepat?", false),
    Message("bisa kak, kami usahakan ya", true),
    Message("Ditunggu ya kak", false),
    Message("Oke kak siap", true),
)