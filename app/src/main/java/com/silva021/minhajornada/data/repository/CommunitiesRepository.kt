package com.silva021.minhajornada.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.silva021.minhajornada.data.datastore.FireStoreHelper
import com.silva021.minhajornada.data.dto.CommentDTO
import com.silva021.minhajornada.data.dto.CommunityDTO
import com.silva021.minhajornada.data.dto.PostDTO
import com.silva021.minhajornada.ui.DatabaseFake
import kotlinx.coroutines.tasks.await

class CommunitiesRepositoryImpl() : CommunitiesRepository {
    val collection by lazy { FireStoreHelper.communities }
    val instance by lazy { FireStoreHelper.db }

    private fun communityRef(communityId: String) =
        collection.document(communityId)

    private fun memberRef(communityId: String, uid: String) =
        communityRef(communityId).collection("members").document(uid)

    private fun userMembershipRef(uid: String, communityId: String) =
        instance.collection("users").document(uid)
            .collection("communityMemberships").document(communityId)

    override suspend fun getMemberCommunityIds(): Set<String> {
        val membersDoc =
            instance.collectionGroup("members").whereEqualTo("uid", Firebase.auth.uid).get().await()
        return membersDoc
            .documents
            .mapNotNull { it.reference.parent.parent?.id }
            .toSet()
    }

    override suspend fun getCommunities(): List<CommunityDTO> {
        return collection.get()
            .await()
            .documents
            .mapNotNull { it.toObject(CommunityDTO::class.java) }
    }

    override suspend fun getCommunityById(id: String): CommunityDTO {
        return collection.document(id)
            .get()
            .await()
            .toObject(CommunityDTO::class.java)
            ?: throw NoSuchElementException("Community with id $id not found")
    }

    override suspend fun getPostsByCommunityId(communityId: String): List<PostDTO> {
        return DatabaseFake.postList
    }

    override suspend fun getPostById(id: String): PostDTO {
        return DatabaseFake.postList.find { it.id == id }!!
    }

    override suspend fun getCommentsByPostId(id: String): List<CommentDTO> {
        return DatabaseFake.comments.filter { it.postId == id }
    }

    override suspend fun joinCommunity(communityId: String) {
        instance.runTransaction { transaction ->
            val uid = Firebase.auth.uid ?: throw Exception("USER_NOT_AUTHENTICATED")
            val communityRef = communityRef(communityId)
            val memberRef = memberRef(communityId, uid)
            val userMembershipRef = userMembershipRef(uid, communityId)

            val commSnap = transaction.get(communityRef)
            if (!commSnap.exists()) error("COMMUNITY_NOT_FOUND")

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
}

interface CommunitiesRepository {
    suspend fun getMemberCommunityIds(): Set<String>
    suspend fun getCommunities(): List<CommunityDTO>
    suspend fun getCommunityById(id: String): CommunityDTO
    suspend fun getPostsByCommunityId(communityId: String): List<PostDTO>
    suspend fun getPostById(id: String): PostDTO
    suspend fun getCommentsByPostId(id: String): List<CommentDTO>
    suspend fun joinCommunity(communityId: String)
}