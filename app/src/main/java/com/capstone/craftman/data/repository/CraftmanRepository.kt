package com.capstone.craftman.data.repository

import com.capstone.craftman.api.ApiService
import com.capstone.craftman.data.fake.Chat
import com.capstone.craftman.data.fake.FakeChat
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