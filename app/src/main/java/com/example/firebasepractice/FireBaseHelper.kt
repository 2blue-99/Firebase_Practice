package com.example.firebasepractice

import android.util.Log
import androidx.activity.result.ActivityResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FireBaseHelper {
    private var db: FirebaseFirestore = Firebase.firestore
    private var auth: FirebaseAuth = Firebase.auth

    fun add(){
        Log.e("TAG", "auth: ${auth.currentUser}", )
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815,
        )
        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.e("TAG", "Success : DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.e("TAG", "Failure : Error adding document", e)
            }
    }

    fun get(){
        db.collection("users")
            .get()
            .addOnSuccessListener { result->
                result.forEach {
                    Log.e("TAG", "get: $it", )
                }
            }
            .addOnFailureListener { exception ->
                Log.e("TAG", "get: $exception", )
            }
    }

    fun login(
        activityResult: ActivityResult,
        onSuccess: () -> Unit
    ) {
        try {
            val account = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
                .getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onSuccess()
                    }
                }
        } catch (e: Exception) {
            Log.e("TAG", "login 실패: $e", )
        }
    }

    fun logout(){ Firebase.auth.signOut() }
    fun isLogin(): Boolean = if(auth.currentUser!=null) true else false
}