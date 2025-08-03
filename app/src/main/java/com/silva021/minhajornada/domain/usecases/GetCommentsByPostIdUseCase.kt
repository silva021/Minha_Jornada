package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.CommunitiesRepository
import com.silva021.minhajornada.domain.model.Comment
import com.silva021.minhajornada.domain.model.Post
import com.silva021.minhajornada.domain.model.toDomain

class GetCommentsByPostIdUseCase(
    val repository: CommunitiesRepository,
) {
    suspend operator fun invoke(id: String): Result<List<Comment>> = runCatching {
        repository.getCommentsByPostId(id).map { it.toDomain() }
    }
}
