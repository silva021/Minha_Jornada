package com.silva021.minhajornada.data.repository

import com.silva021.minhajornada.data.api.CommunitiesApi
import com.silva021.minhajornada.domain.model.Communities
import com.silva021.minhajornada.domain.model.toDomain

interface CommunitiesRepository {
    suspend fun getCommunities(): Communities
}

class CommunitiesRepositoryImpl(
    private val api: CommunitiesApi
) : CommunitiesRepository {
    override suspend fun getCommunities(): Communities {
        return api.getCommunities().toDomain()
    }
}