package com.silva021.minhajornada.data.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.silva021.minhajornada.data.datastore.FireStoreHelper
import com.silva021.minhajornada.data.dto.CommentDTO
import com.silva021.minhajornada.data.dto.PostDTO
import kotlinx.coroutines.tasks.await

class FeedRepositoryImpl : FeedRepository {
    private val db by lazy { FireStoreHelper.db }

    private fun postsRef(communityId: String) =
        db.collection("communities").document(communityId).collection("posts")

    private fun postRef(communityId: String, postId: String) =
        postsRef(communityId).document(postId)

    private fun commentsRef(communityId: String, postId: String) =
        postRef(communityId, postId).collection("comments")

    override suspend fun getFeed(communityId: String): List<PostDTO> {
        val snap = postsRef(communityId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .get()
            .await()

        return snap.documents.mapNotNull { it.toObject(PostDTO::class.java)?.copy(id = it.id) }
    }

    override suspend fun createPost(
        communityId: String,
        postDTO: PostDTO,
    ) {
        postsRef(communityId)
            .document(postDTO.id)
            .set(postDTO)
            .await()
    }

    override suspend fun editPost(
        communityId: String,
        postId: String,
        text: String,
    ) {
        postsRef(communityId)
            .document(postId)
            .update(
                mapOf(
                    "text" to text.trim(),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
            )
            .await()
    }

    override suspend fun deletePost(communityId: String, postId: String) {
        postsRef(communityId)
            .document(postId)
            .delete()
            .await()
    }

    override suspend fun getPostById(
        communityId: String,
        postId: String,
    ): PostDTO {
        return postsRef(communityId)
            .document(postId)
            .get()
            .await()
            .toObject(PostDTO::class.java) ?: throw NoSuchElementException("Post not found")
    }

    override suspend fun getComments(
        communityId: String,
        postId: String
    ): List<CommentDTO> {
        val snap = commentsRef(communityId, postId)
            .orderBy("createdAt", Query.Direction.ASCENDING)
            .get().await()

        return snap.documents.mapNotNull { it.toObject(CommentDTO::class.java)?.copy(id = it.id) }
    }

    override suspend fun createComment(
        communityId: String,
        postId: String,
        commentDTO: CommentDTO
    ) {
        val postDoc = postRef(communityId, postId)
        val commentDoc = commentsRef(communityId, postId).document()

        db.runTransaction { tx ->
            tx.set(commentDoc, commentDTO)
            tx.update(postDoc, "commentsCount", FieldValue.increment(1))
        }.await()
    }

}

interface FeedRepository {
    suspend fun getFeed(communityId: String): List<PostDTO>
    suspend fun createPost(communityId: String, postDTO: PostDTO)
    suspend fun editPost(communityId: String, postId: String, text: String)
    suspend fun deletePost(communityId: String, postId: String)
    suspend fun getPostById(communityId: String, postId: String): PostDTO
    suspend fun getComments(communityId: String, postId: String): List<CommentDTO>
    suspend fun createComment(communityId: String, postId: String, commentDTO: CommentDTO)
}