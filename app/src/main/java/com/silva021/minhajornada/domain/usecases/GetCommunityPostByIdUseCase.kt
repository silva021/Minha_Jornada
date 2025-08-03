package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.CommunitiesRepository
import com.silva021.minhajornada.domain.model.Post
import com.silva021.minhajornada.domain.model.toDomain

class GetCommunityPostByIdUseCase(
    val repository: CommunitiesRepository,
) {
    suspend operator fun invoke(id: String): Result<Post> = runCatching {
        repository.getPostById(id).toDomain()
    }
}