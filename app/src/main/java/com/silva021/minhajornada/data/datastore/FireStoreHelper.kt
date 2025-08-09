package com.silva021.minhajornada.data.datastore

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

object FireStoreHelper {

    val db by lazy { FirebaseFirestore.getInstance() }

    private fun getCollection(collectionName: String): CollectionReference {
        return db.collection(collectionName)
    }

    val usersCollection: CollectionReference
        get() = getCollection("users")

    val userChallengesCollection: CollectionReference
        get() = getCollection("challenges")
            .document(Firebase.auth.currentUser?.uid.orEmpty())
            .collection("userChallenges")

    val publicChallengesCollection: CollectionReference
        get() = getCollection("publicChallenges")

    fun reminderCollection(
        challengeId: String
    ) = getCollection("reminder")

}