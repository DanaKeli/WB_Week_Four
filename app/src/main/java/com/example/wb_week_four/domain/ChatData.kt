package com.example.wb_week_four.domain

data class ChatData(
    val id: Int = 0,
    val chatName: String = "",
    var time: String = "",
    val unreadMessages: Int = 0,
    val messages: MutableList<String> = mutableListOf(""),
    val logo: Int = 0,
    val logoColor: Int = 0
)