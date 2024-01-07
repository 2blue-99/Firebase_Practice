package com.example.firebasepractice

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.firebasepractice.screen.MainScreen
import com.example.firebasepractice.ui.theme.FireBasePracticeTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.firebasepractice.screen.LoginScreen
import com.example.firebasepractice.screen.navigateLoginScreen
import com.example.firebasepractice.screen.navigateMainScreen
import com.example.firebasepractice.state.ScreenState

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FireBasePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val fireBase = FireBaseHelper()

    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = if(fireBase.isLogin()) ScreenState.Main.name else ScreenState.Login.name,
            modifier = Modifier.padding(padding)
        ){
            composable(route = ScreenState.Login.name){
                LoginScreen(db = fireBase){
                    navController.navigateMainScreen()
                }
            }
            composable(route = ScreenState.Main.name){
                MainScreen(db = fireBase){
                    navController.navigateLoginScreen()
                }
            }
        }
    }
}