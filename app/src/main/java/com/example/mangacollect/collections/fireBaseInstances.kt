package com.example.mangacollect.collections

import android.annotation.SuppressLint
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

object fireBaseInstances {


    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private val firebaseAuth = FirebaseAuth.getInstance()
    fun getFireStoreInstance() : FirebaseFirestore{
        return firebaseFirestore
    }
    fun getFirebaseAuthInstance(): FirebaseAuth{
        return firebaseAuth
    }
}