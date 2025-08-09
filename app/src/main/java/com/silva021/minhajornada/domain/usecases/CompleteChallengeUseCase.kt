package com.silva021.minhajornada.domain.usecases

import com.silva021.minhajornada.data.repository.ChallengeRepository
import com.silva021.minhajornada.domain.model.Challenge
import com.silva021.minhajornada.domain.model.ChallengeResult
import com.silva021.minhajornada.domain.model.CheckInStatus
import com.silva021.minhajornada.domain.model.DurationType.*

class CompleteChallengeUseCase(
    private val getChallengeByIdUseCase: GetChallengeByIdUseCase,
    private val repository: ChallengeRepository
) {
    suspend operator fun invoke(
        challengeId: String
    ): Result<Pair<Challenge, ChallengeResult>> {
        return runCatching {
            val challenge = getChallengeByIdUseCase(challengeId).getOrThrow()

            val failures = challenge.checkins.count { it.status == CheckInStatus.FAILURE }
            val maxFails = challenge.allowedFailures()

            val result = when {
                failures <= maxFails -> ChallengeResult.SUCCESS
                else -> ChallengeResult.FAILED
            }

            repository.completeChallenge(challengeId)


            Pair(challenge, result)
        }.onFailure { exception -> throw exception }
    }

    private fun Challenge.allowedFailures(): Int = when (this.durationType) {
        THREE_DAYS -> 0
        SEVEN_DAYS -> 1
        FOURTEEN_DAYS -> 2
        TWENTY_ONE_DAYS -> 3
        THIRTY_DAYS -> 5
    }
}