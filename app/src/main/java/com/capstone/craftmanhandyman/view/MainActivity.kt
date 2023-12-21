package com.capstone.craftmanhandyman.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.craftmanhandyman.data.preference.UserModel
import com.capstone.craftmanhandyman.data.repository.CraftmanRepository
import com.capstone.craftmanhandyman.view.ui.theme.CraftmanHandymanTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getSession().observe(this) { user ->

            setContent {
                CraftmanHandymanTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        if (!user.isLogin){
                            LoginRegisterApp()
                        }else{
                            CraftmanApp()
                        }
                    }
                }
            }

        }
    }
}

class MainViewModel(private val repository: CraftmanRepository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}
