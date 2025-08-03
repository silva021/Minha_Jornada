package com.silva021.minhajornada.ui.routes

import androidx.navigation.NavController
import com.silva021.minhajornada.domain.model.ChallengeResult

sealed class Routes(val route: String) {
    fun popBackStack(navController: NavController) {
        navController.popBackStack()
    }

    object ProfileScreen : Routes("profile_screen") {
        fun navigateToProfileScreen(navController: NavController) {
            navController.navigate(ProfileScreen.route) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    object ChallengesScreen : Routes("challenges_screen") {
        fun navigateToChallengesScreen(navController: NavController) {
            navController.navigate(ChallengesScreen.route) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    object CreateChallengesScreen : Routes("create_challenges_screen") {
        fun navigateToCreateChallengesScreen(navController: NavController) {
            navController.navigate(CreateChallengesScreen.route)
        }
    }

    object FeedbackScreen : Routes("feedback_screen") {
        fun navigateToFeedbackScreen(navController: NavController) {
            navController.navigate(FeedbackScreen.route)
        }
    }

    object HelpScreen : Routes("help_screen") {
        fun navigateToHelpScreen(navController: NavController) {
            navController.navigate(HelpScreen.route)
        }
    }

    object UpdateChallengeProgressScreen : Routes("update_challenge_progress_screen") {
        fun navigateToUpdateChallengeProgressScreen(navController: NavController) {
            navController.navigate(UpdateChallengeProgressScreen.route)
        }
    }

    object CommunitiesScreen : Routes("communities_screen") {
        fun navigateToCommunitiesScreen(navController: NavController) {
            navController.navigate(CommunitiesScreen.route) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    object ExplorerScreen : Routes("explorer_screen") {
        fun navigateToExplorerScreen(navController: NavController) {
            navController.navigate(ExplorerScreen.route) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    object ExplorerChallengeDetailsScreen : Routes("explorer_challenge_details_screen") {
        fun navigateToExplorerChallengeDetailsScreen(navController: NavController) {
            navController.navigate(ExplorerChallengeDetailsScreen.route)
        }
    }
    object CommunityDetailsScreen : Routes("community_details_screen") {
        fun navigateToCommunityDetailsScreen(navController: NavController) {
            navController.navigate(CommunityDetailsScreen.route)
        }
    }

    object CommunityFeedScreen : Routes("community_feed_screen") {
        fun navigateToCommunityFeedScreen(navController: NavController) {
            navController.navigate(CommunityFeedScreen.route)
        }
    }
    object CommunityPostScreen : Routes("community_post_screen") {
        fun navigateToCommunityPostScreen(navController: NavController) {
            navController.navigate(CommunityPostScreen.route)
        }
    }

    object ChallengeSummaryScreen : Routes("challenge_summary_screen") {
        fun navigateToChallengeSummaryScreen(navController: NavController) {
            navController.navigate(ChallengeSummaryScreen.route)
        }
    }

    object ChallengeCompletedScreen : Routes("challenge_completed_screen/{status}") {
        const val STATUS_ID = "status"

        fun navigateToChallengeCompletedScreen(navController: NavController, challengeResult: ChallengeResult) {
            navController.navigate(ChallengeCompletedScreen.route.replace("{$STATUS_ID}", challengeResult.name))
        }
    }

    object ChallengeFailedScreen : Routes("challenge_failed_screen") {
        fun navigateToChallengeFailedScreen(navController: NavController) {
            navController.navigate(ChallengeFailedScreen.route)
        }
    }

    object WelcomeScreen : Routes("welcome_screen")
}
