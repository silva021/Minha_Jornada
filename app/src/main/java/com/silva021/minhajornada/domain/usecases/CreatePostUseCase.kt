package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.dto.PostDTO
import com.silva021.minhajornada.data.repository.ChallengeRepository
import com.silva021.minhajornada.data.repository.FeedRepository
import com.silva021.minhajornada.data.repository.PublicChallengeRepository
import com.silva021.minhajornada.domain.model.Profile
import com.silva021.minhajornada.ui.DatabaseFake
import java.util.UUID

class CreatePostUseCase(
    private val repository: FeedRepository,
) {
    suspend operator fun invoke(
        communityId: String,
        profile: Profile,
        text: String,
    ) = runCatching {

        repository.createPost(
            communityId = communityId,
            PostDTO(
                id = UUID.randomUUID().toString(),
                authorId = profile.id,
                authorName = profile.name,
                authorImage = profile.profilePictureUrl,
                authorUserName = profile.userName,
                text = text,
            )
        )
    }
}