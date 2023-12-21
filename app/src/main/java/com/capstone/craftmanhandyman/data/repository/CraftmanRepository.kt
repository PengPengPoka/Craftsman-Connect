package com.capstone.craftmanhandyman.data.repository

import com.capstone.craftmanhandyman.data.fake.Chat
import com.capstone.craftmanhandyman.data.fake.FakeChat
import com.capstone.craftmanhandyman.data.fake.FakeHistory
import com.capstone.craftmanhandyman.data.fake.History
import com.capstone.craftmanhandyman.data.fake.Message
import com.capstone.craftmanhandyman.data.fake.chatMessages
import com.capstone.craftmanhandyman.data.preference.UserModel
import com.capstone.craftmanhandyman.data.preference.UserPreference
import kotlinx.coroutines.flow.Flow

class CraftmanRepository(
    private val userPreference: UserPreference,
) {

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    fun getChat() : List<Chat>{
        return FakeChat.dummyChat
    }

    fun getHistory() : List<History>{
        return FakeHistory.dummyHistory
    }

    fun getMessages(): List<Message> {
        return chatMessages
    }

    // Fungsi untuk menambahkan pesan baru ke dalam chatMessages
    fun addMessage(message: Message) {
        chatMessages.toMutableList().add(message)
    }
    companion object {
        @Volatile
        private var instance: CraftmanRepository? = null
        fun getInstance(
            userPreference: UserPreference
        ): CraftmanRepository =
            instance ?: synchronized(this) {
                instance ?: CraftmanRepository(userPreference)
            }.also { instance = it }
    }
}