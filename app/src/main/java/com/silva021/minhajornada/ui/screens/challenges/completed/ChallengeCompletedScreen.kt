package com.silva021.minhajornada.ui.screens.challenges.completed

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.silva021.minhajornada.domain.model.ChallengeResult

@Composable
fun ChallengeCompletedScreen(
    challengeResult: ChallengeResult,
    primaryButtonOnClick: () -> Unit = {},
    secondaryButtonOnClick: () -> Unit = {},
    onBackPressed: () -> Unit,
) {
    when (challengeResult) {
        ChallengeResult.SUCCESS -> ChallengeCompletedContent(
            title = "Parabéns, você conseguiu!",
            description = "Você completou com sucesso o 'Desafio de Fitness 30 Dias'. Seu esforço e dedicação valeram a pena. Continue com o bom trabalho!",
            insightDescription = "O fim de um desafio pode ser o começo de um novo hábito. Qual será seu próximo passo?",
            isSuccess = true,
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuB6IV2MwBKRqDJXBSP-V2ponakBZn6RkBJvgvofNIlXqAM5AKyBzT0c8VzAP3rDHgtzWmzaH6COZLyinsazbDNDSFAJK-HwqUCNQAwzowtEOBTDO0Lj21K38lPdD2SjSEFiJxeUekvUQ1-VoVMm1HKCKiHsyEgVYNoKj7mHjkpRIAFYs39t3oMyYWomEL3-tnkc6X5YjZp3BkdLW2XuzxEqiEEAyaeUZtTFeZQG06s4CD_Of1OiNcVZxdVXwpJJVgrry0uwn-pas18",
            primaryButtonText = "Voltar para o Início",
            primaryButtonOnClick = primaryButtonOnClick,
            secondaryButtonText = null,
            secondaryButtonOnClick = null,
            onBackPressed = onBackPressed
        )

        ChallengeResult.FAILED -> ChallengeCompletedContent(
            title = "Não desista!",
            description = "Está tudo bem em tropeçar. Cada tentativa te aproxima do sucesso.",
            insightDescription = "Nem tudo sai como planejado — e tudo bem. O que esse desafio te ensinou? Existe algo que você faria diferente em uma nova tentativa?",
            isSuccess = false,
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDNaILYqcRrJSIUE0ClGPEMO_LwSv8U1hFPzm3BddbR032-N6RcXiYvLZO-g1eULpNhIqaprhiyY_iKPwVYE5qtg9PYFxcRhpuO8JWyAjBhMr7oN0K-o42E7gcRA-batudjcJybJWt_18M1UWLr49OOeAV7LpgPqrwsxARBANrPbBfKVmAWR9S8P8jwDWtUqopPN7Oa3Tv8Fbx5bZaAWStj3ExT09H0IoAkynwZpgJDfHV1-D-ihdEyPlMcn-Y-xzPnmfhxqYmXk2M",
            primaryButtonText = "Tentar Novamente",
            primaryButtonOnClick = primaryButtonOnClick,
            secondaryButtonText = "Explorar Novos Desafios",
            secondaryButtonOnClick = secondaryButtonOnClick,
            onBackPressed = onBackPressed
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ChallengeCompletedSuccessScreenPreview() {
    MaterialTheme {
        ChallengeCompletedScreen(
            challengeResult = ChallengeResult.SUCCESS,
            onBackPressed = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChallengeCompletedFailedScreenPreview() {
    MaterialTheme {
        ChallengeCompletedScreen(
            challengeResult = ChallengeResult.FAILED,
            onBackPressed = {}
        )
    }
}