package com.example.firebasepractice.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.firebasepractice.FireBaseHelper
import com.example.firebasepractice.state.ScreenState


@Composable
fun MainScreen(
    db : FireBaseHelper,
    navigate: () -> Unit
) {
    val fireBaseHelper = FireBaseHelper()

    Surface(
    modifier = Modifier.fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.size(100.dp,50.dp),
                onClick = { fireBaseHelper.add() }
            ) {
                Text(text = "Put")
            }

            Button(
                modifier = Modifier.size(100.dp,50.dp),
                onClick = { fireBaseHelper.get() }
            ) {
                Text(text = "Get")
            }

            Button(
                modifier = Modifier.size(100.dp,50.dp),
                onClick = {
                    db.logout()
                    if(!db.isLogin())
                        navigate()
                }
            ) {
                Text(text = "Logout")
            }
        }
    }
}

fun NavController.navigateMainScreen(){
    this.navigate(ScreenState.Main.name)
}