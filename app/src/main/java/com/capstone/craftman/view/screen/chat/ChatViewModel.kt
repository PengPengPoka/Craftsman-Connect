package com.capstone.craftman.view.screen.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.craftman.data.fake.Chat
import com.capstone.craftman.data.repository.CraftmanRepository

class ChatViewModel(private val repository: CraftmanRepository): ViewModel() {
    private val _chatList = MutableLiveData<List<Chat>>()
    val chatList: LiveData<List<Chat>> = _chatList

    fun fetchChat() {

        val chatData = repository.getChat()
        _chatList.value = chatData
    }
}