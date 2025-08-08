package com.silva021.minhajornada.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.silva021.minhajornada.data.datastore.FireStoreHelper
import com.silva021.minhajornada.data.dto.ChallengeDTO
import com.silva021.minhajornada.data.dto.ReminderDTO
import kotlinx.coroutines.tasks.await

class ReminderRepositoryImpl() : ReminderRepository {
    val userChallengesCollection by lazy { FireStoreHelper.userChallengesCollection }
    override suspend fun updateReminder(
        challengeId: String,
        updated: ReminderDTO
    ) {
        val ref = userChallengesCollection.document(challengeId)

        FireStoreHelper.db.runTransaction { transaction ->
            val snap = transaction.get(ref)
            val challenge = snap.toObject(ChallengeDTO::class.java)
                ?: throw Exception("Challenge not found")

            val idx = challenge.reminders.indexOfFirst { it.id == updated.id }
            if (idx == -1) throw Exception("Reminder not found")

            val newList = challenge.reminders.toMutableList().apply {
                this[idx] = updated
            }.toList()

            transaction.update(ref, "reminders", newList)
            null
        }.await()
    }

    override suspend fun createReminder(
        challengeId: String,
        reminder: ReminderDTO,
    ) {
        val reminderDocument = userChallengesCollection.document(challengeId)
        val challengeDTO = reminderDocument.get().await().toObject(ChallengeDTO::class.java) ?: throw Exception("Challenge not found")
        val reminders = challengeDTO.reminders.toMutableList()
        reminders.add(reminder)

        reminderDocument.update(
            mapOf("reminders" to reminders.toList())
        ).await()
    }

    override suspend fun deleteReminder(challengeId: String, reminderId: String) {
        val reminderDocument = userChallengesCollection.document(challengeId)
        val challengeDTO = reminderDocument.get().await().toObject(ChallengeDTO::class.java) ?: throw Exception("Challenge not found")
        val reminders = challengeDTO.reminders.toMutableList()
        val reminderToDelete = reminders.find { it.id == reminderId } ?: throw Exception("Reminder not found")
        reminders.remove(reminderToDelete)

        reminderDocument.update(
            mapOf("reminders" to reminders.toList())
        ).await()
    }

    override suspend fun getReminderById(challengeId: String, reminderId: String) : ReminderDTO {
        val reminderDocument = userChallengesCollection.document(challengeId)
        val reminders = reminderDocument.get().await().toObject(ChallengeDTO::class.java)?.reminders ?: throw Exception("Challenge not found")
        return reminders.find { it.id == reminderId } ?: throw Exception("Reminder not found")

    }
}

interface ReminderRepository {
    suspend fun createReminder(
        challengeId: String,
        reminder: ReminderDTO,
    )
    suspend fun deleteReminder(
        challengeId: String,
        reminderId: String,
    )

    suspend fun getReminderById(
        challengeId: String,
        reminderId: String,
    ) : ReminderDTO

    suspend fun updateReminder(
        challengeId: String,
        updated: ReminderDTO
    )
}