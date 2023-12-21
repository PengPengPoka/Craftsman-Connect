package com.capstone.craftmanhandyman.view.screen.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.craftmanhandyman.data.fake.Chat
import com.capstone.craftmanhandyman.data.fake.Message
import com.capstone.craftmanhandyman.data.repository.CraftmanRepository

class ChatViewModel(private val repository: CraftmanRepository): ViewModel() {
    private val _chatList = MutableLiveData<List<Chat>>()
    val chatList: LiveData<List<Chat>> = _chatList

    fun fetchChat() {

        val chatData = repository.getChat()
        _chatList.value = chatData
    }


    private val _chatMessage = MutableLiveData<List<Message>>()
    val chatMessage: LiveData<List<Message>> = _chatMessage

    fun fetchChatMessage() {

        val chatData = repository.getMessages()
        _chatMessage.value = chatData
    }

    fun addMessage(message: Message) {
        repository.addMessage(message)
        fetchChatMessage()
    }
}