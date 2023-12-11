package com.capstone.craftman.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.craftman.data.injection.Injection
import com.capstone.craftman.data.repository.CraftmanRepository
import com.capstone.craftman.view.screen.chat.ChatViewModel
import com.capstone.craftman.view.screen.detailcraftman.DetailViewModel
import com.capstone.craftman.view.screen.history.HistoryViewModel
import com.capstone.craftman.view.screen.list_craftman.ListCraftmanViewModel
import com.capstone.craftman.view.screen.login.LoginViewModel
import com.capstone.craftman.view.screen.order.PesananViewModel
import com.capstone.craftman.view.screen.profile.ProfileViewModel
import com.capstone.craftman.view.screen.register.RegisterViewModel

class ViewModelFactory(private val repository: CraftmanRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ChatViewModel::class.java) -> {
                ChatViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ListCraftmanViewModel::class.java) -> {
                ListCraftmanViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(PesananViewModel::class.java) -> {
                PesananViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}