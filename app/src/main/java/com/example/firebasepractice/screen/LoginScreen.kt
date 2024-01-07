package com.example.firebasepractice.screen

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.firebasepractice.BuildConfig
import com.example.firebasepractice.FireBaseHelper
import com.example.firebasepractice.MainViewModel
import com.example.firebasepractice.R
import com.example.firebasepractice.state.ScreenState
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@Composable
fun LoginScreen(
    db : FireBaseHelper,
    viewModel: MainViewModel = MainViewModel(),
    navigate: () -> Unit
) {

    val context = LocalContext.current
    val token = BuildConfig.MyKey
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        db.login(activityResult = it) {
            Log.e("TAG", "AuthScreen:로그인 완료", )
            Toast.makeText(context, "로그인이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            navigate()
        }
    }

    Surface(
        modifier = Modifier
            .background(Color.Gray)
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.size(100.dp, 50.dp),
                onClick = {
                    val googleSignInOptions = GoogleSignInOptions
                        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(token)
                        .requestEmail()
                        .build()
                    val googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)
                    launcher.launch(googleSignInClient.signInIntent)
                }) {
                Text(text = "Login")
            }
        }
    }
}
fun NavController.navigateLoginScreen(){
    this.navigate(ScreenState.Login.name)
}