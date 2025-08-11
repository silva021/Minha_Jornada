package com.silva021.minhajornada.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.silva021.minhajornada.data.datastore.FireStoreHelper
import com.silva021.minhajornada.data.dto.CommunityDTO
import com.silva021.minhajornada.domain.model.CategoryType
import kotlinx.coroutines.tasks.await

class CommunitiesRepositoryImpl() : CommunitiesRepository {
    val collection by lazy { FireStoreHelper.communities }
    val instance by lazy { FireStoreHelper.db }

    private fun communityRef(communityId: String) =
        collection
            .document(communityId)

    private fun memberRef(communityId: String, uid: String) =
        communityRef(communityId)
            .collection("members")
            .document(uid)

    private fun userMembershipRef(uid: String, communityId: String) =
        instance
            .collection("users")
            .document(uid)
            .collection("communityMemberships")
            .document(communityId)

    override suspend fun getMemberCommunityIds(): List<String> {
        val membersDoc = instance
            .collection("users")
            .document(Firebase.auth.uid.orEmpty())
            .collection("communityMemberships")


        return membersDoc
            .get()
            .await()
            .mapNotNull { it.reference.id }
    }

    override suspend fun getCommunities(categoryType: CategoryType): List<CommunityDTO> {
        var query: Query = collection

        if (categoryType != CategoryType.ALL) {
            query = query
                .whereEqualTo("category", categoryType.name)
        }

        return query
            .get()
            .await()
            .documents
            .mapNotNull {
                it.toObject(CommunityDTO::class.java)
            }
    }

    override suspend fun getCommunityById(id: String): CommunityDTO {
        return collection.document(id)
            .get()
            .await()
            .toObject(CommunityDTO::class.java)
            ?: throw NoSuchElementException("Community with id $id not found")
    }

    override suspend fun getCommunitiesByIds(
        ids: Set<String>,
        categoryType: CategoryType,
    ): List<CommunityDTO> {
        if (ids.isEmpty()) return emptyList()

        val fetched = mutableListOf<CommunityDTO>()

        for (batch in ids.chunked(10)) {
            var query: Query = collection

            if (categoryType != CategoryType.ALL)
                query = query.whereEqualTo("category", categoryType.name)

            val newsCommunities = query
                .whereIn(FieldPath.documentId(), batch)
                .get()
                .await()
                .documents

            fetched += newsCommunities.mapNotNull {
                it.toObject(CommunityDTO::class.java)
            }
        }

        return fetched
    }

    override suspend fun joinCommunity(communityId: String) {
        instance.runTransaction { transaction ->
            val uid = Firebase.auth.uid ?: throw Exception("USER_NOT_AUTHENTICATED")
            val communityRef = communityRef(communityId)
            val memberRef = memberRef(communityId, uid)
            val userMembershipRef = userMembershipRef(uid, communityId)

            val communityDocument = transaction.get(communityRef)
            if (!communityDocument.exists()) error("COMMUNITY_NOT_FOUND")

            val already = transaction.get(memberRef).exists()
            if (already) return@runTransaction Unit

            transaction.set(
                memberRef,
                mapOf(
                    "userId" to uid,
                    "role" to "member",
                    "joinedAt" to FieldValue.serverTimestamp()
                )
            )
            transaction.set(
                userMembershipRef,
                mapOf(
                    "communityId" to communityId,
                    "role" to "member",
                    "joinedAt" to FieldValue.serverTimestamp()
                )
            )

            transaction.update(communityRef, "membersCount", FieldValue.increment(1))
            Unit
        }.await()
    }

    override suspend fun isMember(communityId: String): Boolean {
        val uid = Firebase.auth.uid ?: return false
        return memberRef(communityId, uid).get().await().exists()
    }

    override suspend fun leaveCommunity(communityId: String): Result<Unit> = runCatching {
        FireStoreHelper.db.runTransaction { transaction ->
            val uid = Firebase.auth.uid ?: throw Exception("USER_NOT_AUTHENTICATED")

            val communityRef = communityRef(communityId)
            val memberRef = memberRef(communityId, uid)
            val userMembershipRef = userMembershipRef(uid, communityId)

            if (!transaction.get(communityRef).exists()) return@runTransaction Unit

            val exists = transaction.get(memberRef).exists()
            if (!exists) return@runTransaction Unit

            transaction.delete(memberRef)
            transaction.delete(userMembershipRef)

            transaction.update(communityRef, "membersCount", FieldValue.increment(-1))
            Unit
        }.await()
    }
}

interface CommunitiesRepository {
    suspend fun getMemberCommunityIds(): List<String>
    suspend fun getCommunities(categoryType: CategoryType): List<CommunityDTO>
    suspend fun getCommunityById(id: String): CommunityDTO
    suspend fun getCommunitiesByIds(
        ids: Set<String>,
        categoryType: CategoryType = CategoryType.ALL,
    ): List<CommunityDTO>

    suspend fun joinCommunity(communityId: String)
    suspend fun leaveCommunity(communityId: String): Result<Unit>
    suspend fun isMember(communityId: String): Boolean
}