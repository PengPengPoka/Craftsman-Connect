package com.capstone.craftmanhandyman.data.injection

import android.content.Context
import com.capstone.craftmanhandyman.data.preference.UserPreference
import com.capstone.craftmanhandyman.data.preference.dataStore
import com.capstone.craftmanhandyman.data.repository.CraftmanRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): CraftmanRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        return CraftmanRepository.getInstance(pref)
    }
}