package com.example.firebasepractice

import android.app.Instrumentation
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainViewModel: ViewModel() {


    fun login(
        activityResult: ActivityResult,
        auth: FirebaseAuth,
        onSuccess: () -> Unit
    ) {
        try {
            val account = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
                .getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.e("TAG", "login: 성공적", )
                        onSuccess()
                    }
                }
        } catch (e: Exception) {
            Log.e("TAG", "login 실패: $e", )
        }
    }
}