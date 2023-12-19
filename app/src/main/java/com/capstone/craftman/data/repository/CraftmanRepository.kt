package com.capstone.craftman.data.repository

import androidx.lifecycle.liveData
import com.capstone.craftman.api.ApiService
import com.capstone.craftman.api.LoginResponse
import com.capstone.craftman.api.RegisterResponse
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
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class CraftmanRepository(
    private val userPreference: UserPreference,
    private val apiService: ApiService
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

    fun login(email: String, password: String) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.login(email, password)
            emit(ResultState.Success(successResponse))
        }catch (e: HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun register(nama: String, email: String, noHp: String, password: String) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.register(nama, email, noHp, password)
            emit(ResultState.Success(successResponse))
        }catch (e: HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }
    companion object {
        @Volatile
        private var instance: CraftmanRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): CraftmanRepository =
            instance ?: synchronized(this) {
                instance ?: CraftmanRepository(userPreference, apiService)
            }.also { instance = it }
    }


}