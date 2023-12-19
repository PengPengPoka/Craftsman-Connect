package com.capstone.craftman.data.injection

import android.content.Context
import com.capstone.craftman.api.ApiConfig
import com.capstone.craftman.data.preference.UserPreference
import com.capstone.craftman.data.preference.dataStore
import com.capstone.craftman.data.repository.CraftmanRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): CraftmanRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return CraftmanRepository.getInstance(pref, apiService)
    }
}