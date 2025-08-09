package com.silva021.minhajornada.data.repository

import com.silva021.minhajornada.data.datastore.FireStoreHelper
import com.silva021.minhajornada.data.dto.ChallengeDTO
import com.silva021.minhajornada.data.dto.CheckInDTO
import kotlinx.coroutines.tasks.await
import java.util.UUID

class CheckInRepositoryImpl() : CheckInRepository {
    val userChallengesCollection by lazy { FireStoreHelper.userChallengesCollection }

    override suspend fun upsertCheckIn(
        challengeId: String,
        checkIn: CheckInDTO
    ) {
        val ref = userChallengesCollection.document(challengeId)

        FireStoreHelper.db.runTransaction { tx ->
            val snap = tx.get(ref)
            val challenge = snap.toObject(ChallengeDTO::class.java)
                ?: throw Exception("Challenge not found")

            val current = challenge.checkins.toMutableList()

            val idx = current.indexOfFirst { it.id == checkIn.id }
            if (idx >= 0) {
                current[idx] = checkIn.copy()
            } else {
                val toInsert = if (checkIn.id.isBlank())
                    checkIn.copy(id = UUID.randomUUID().toString())
                else
                    checkIn

                current.add(toInsert)
            }

            tx.update(ref, "checkins", current.toList())
            null
        }.await()
    }
}

interface CheckInRepository {
    suspend fun upsertCheckIn(
        challengeId: String,
        checkIn: CheckInDTO
    )
}