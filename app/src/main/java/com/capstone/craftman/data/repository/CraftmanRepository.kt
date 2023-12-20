package com.capstone.craftman.data.repository

import androidx.lifecycle.liveData
import com.capstone.craftman.api.ApiService
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
import com.capstone.craftman.helper.UiState
import com.capstone.craftman.response.LoginRequest
import com.capstone.craftman.response.LoginResponse
import com.capstone.craftman.response.RegisterRequest
import com.capstone.craftman.response.RegisterResponse
import com.capstone.craftman.response.CraftmanResponse
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


    suspend fun login(email: String, password: String) = liveData {
        emit(UiState.Loading)
        try {
            val loginRequest = LoginRequest(email,password)
            val successResponse = apiService.login(loginRequest)
            emit(UiState.Success(successResponse))
        }catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
            emit(UiState.Error(errorResponse.toString()))
        } catch (e: Exception) {
            emit(UiState.Error("Error : ${e.message.toString()}"))
        }
    }


    suspend fun register(nama : String, email: String, noHp : String, password: String) = liveData {
        emit(UiState.Loading)
        try {
            val registerRequest = RegisterRequest(nama, email, noHp, password)
            val successResponse = apiService.register(registerRequest)
            emit(UiState.Success(successResponse))
        }catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
            emit(UiState.Error(errorResponse.toString()))
        } catch (e: Exception) {
            emit(UiState.Error("Error : ${e.message.toString()}"))
        }
    }

    suspend fun getTukang() = liveData {
        emit(UiState.Loading)
        try {
            val successResponse = apiService.getCraftman()
            emit(UiState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, CraftmanResponse::class.java)
            emit(UiState.Error(errorResponse.toString()))
        } catch (e: Exception) {
            emit(UiState.Error("Error : ${e.message.toString()}"))
        }

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
            userPreference: UserPreference,
            apiService: ApiService
        ): CraftmanRepository =
            instance ?: synchronized(this) {
                instance ?: CraftmanRepository(userPreference, apiService)
            }.also { instance = it }
    }
}