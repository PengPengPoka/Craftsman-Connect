package com.capstone.craftman.data.repository

import com.capstone.craftman.data.fake.Chat
import com.capstone.craftman.data.fake.Craftmans
import com.capstone.craftman.data.fake.FakeChat
import com.capstone.craftman.data.fake.FakeCraftman
import com.capstone.craftman.data.fake.FakeCraftman.dummyCraftmans
import com.capstone.craftman.data.fake.FakeHistory
import com.capstone.craftman.data.fake.History
import com.capstone.craftman.data.fake.Message
import com.capstone.craftman.data.fake.chatMessages
import com.capstone.craftman.data.preference.UserModel
import com.capstone.craftman.data.preference.UserPreference
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

    fun getCraftman() : List<Craftmans>{
        return FakeCraftman.dummyCraftmans
    }

    fun getCraftmanByName(name: String): Craftmans? {
        return dummyCraftmans.find { it.name == name }
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