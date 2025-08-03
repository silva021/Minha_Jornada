package com.silva021.minhajornada.domain.model

data class PublicChallenges(
    val popularChallenges: List<PublicChallenge>,
    val trendingChallenges: List<PublicChallenge>,
)