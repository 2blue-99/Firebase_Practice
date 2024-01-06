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
import com.example.firebasepractice.FireBaseHelper


@Composable
fun MainScreen() {
    val fireBaseHelper = FireBaseHelper()
    fireBaseHelper.maker()

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
        }
    }
}