package com.example.firebasepractice

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FireBaseHelper {
    private lateinit var db: FirebaseFirestore

    fun maker(){
        db = Firebase.firestore
    }

    fun add(){
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815,
        )

        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.e("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.e("TAG", "Error adding document", e)
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




}