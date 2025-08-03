package com.silva021.minhajornada.data.repository

import com.silva021.minhajornada.data.api.CommunitiesApi
import com.silva021.minhajornada.data.dto.CommentDTO
import com.silva021.minhajornada.data.dto.CommunitiesDTO
import com.silva021.minhajornada.data.dto.PostDTO
import com.silva021.minhajornada.domain.model.Comment
import com.silva021.minhajornada.domain.model.Communities
import com.silva021.minhajornada.domain.model.Post
import com.silva021.minhajornada.domain.model.toDomain
import com.silva021.minhajornada.ui.DatabaseFake
import com.silva021.minhajornada.ui.DatabaseFake.communities

interface CommunitiesRepository {
    suspend fun getCommunities(): CommunitiesDTO
    suspend fun getPostsByCommunityId(communityId: String): List<PostDTO>
    suspend fun getPostById(id: String): PostDTO
    suspend fun getCommentsByPostId(id: String): List<CommentDTO>
}

class CommunitiesRepositoryImpl(
    private val api: CommunitiesApi
) : CommunitiesRepository {
    override suspend fun getCommunities(): CommunitiesDTO {
//        return api.getCommunities()
        return communities
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
}