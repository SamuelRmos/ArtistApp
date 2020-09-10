package com.example.sampleapp.repository

import android.util.Log
import com.example.sampleapp.model.User
import com.example.sampleapp.util.Messages
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserRepository {
    private lateinit var database: DatabaseReference

    fun insertUser(
        id: String, name: String, email: String, password: String, birth: String
    ) {
        database = Firebase.database.reference
        val user = User(name, email, password, birth)

        database.child("users").child(id).setValue(user)
            .addOnSuccessListener {
                Log.d(TAG, Messages.insertSuccess)
            }
            .addOnFailureListener {
                Log.d(TAG, Messages.insertError)
            }
    }

    companion object {
        private const val TAG = "UserRepository"
    }
}