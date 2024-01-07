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
    var born = 1815
    private var db: FirebaseFirestore = Firebase.firestore
    private var auth: FirebaseAuth = Firebase.auth

    fun add(){

        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal"),
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal"),
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast"),
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu"),
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei"),
        )
        cities.document("BJ").set(data5)
    }

    fun get() {
        Log.e("TAG", "get")

        db.collection("cities").whereEqualTo("capital", true)
            .get()
            .addOnSuccessListener {
                it.forEach { Log.e("TAG", "${it.id} => ${it.data}")}
            }

//        db.collection("cities")
//            .whereEqualTo("capital", true)
//            .get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    Log.e("TAG", "${document.id} => ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.e("TAG", "Error getting documents: ", exception)
//            }
//        db.collection("users")
//            .get()
//            .addOnSuccessListener { result->
//                result.forEach {
//                    Log.e("TAG", "get: $it", )
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.e("TAG", "get: $exception", )
//            }
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