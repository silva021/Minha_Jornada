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

    object ExplorerChallengeDetailsScreen : Routes("explorer_challenge_details_screen/{challengeId}") {
        const val CHALLENGE_ID = "challengeId"

        fun navigateToExplorerChallengeDetailsScreen(navController: NavController, challengeId: String) {
            navController.navigate(ExplorerChallengeDetailsScreen.route.replace("{$CHALLENGE_ID}", challengeId))
        }
    }
    object CommunityDetailsScreen : Routes("community_details_screen/{communityId}") {
        const val COMMUNITY_ID = "communityId"

        fun navigateToCommunityDetailsScreen(navController: NavController, communityId: String) {
            navController.navigate(CommunityDetailsScreen.route.replace("{$COMMUNITY_ID}", communityId))
        }
    }

    object CommunityFeedScreen : Routes("community_feed_screen/{communityId}") {
        const val COMMUNITY_ID = "communityId"
        fun navigateToCommunityFeedScreen(navController: NavController, communityId: String) {
            navController.navigate(CommunityFeedScreen.route.replace("{$COMMUNITY_ID}", communityId))
        }
    }
    object CommunityPostScreen : Routes("community_post_screen/{postId}") {
        const val POST_ID = "postId"
        fun navigateToCommunityPostScreen(navController: NavController, postId: String) {
            navController.navigate(CommunityPostScreen.route.replace("{$POST_ID}", postId))
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

    object SignUpScreen : Routes("signup_screen") {
        fun navigateToSignUpScreen(navController: NavController) {
            navController.navigate(SignUpScreen.route)
        }
    }

    object EditPostScreen : Routes("edit_post_screen/{postId}") {
        const val POST_ID = "postId"

        fun navigateToEditPostScreen(navController: NavController, postId: String) {
            navController.navigate(EditPostScreen.route.replace("{$POST_ID}", postId))
        }
    }

    object EditProfileScreen : Routes("edit_profile_screen") {
        fun navigateToEditProfileScreen(navController: NavController) {
            navController.navigate(EditProfileScreen.route)
        }
    }

    object LoginScreen : Routes("login_screen")
    object WelcomeScreen : Routes("welcome_screen")

    object CreateReminderScreen : Routes("create_reminder_screen") {
        fun navigateToCreateReminderScreen(navController: NavController) {
            navController.navigate(CreateReminderScreen.route)
        }
    }
    object RemindersScreen : Routes("reminders_screen/{challengeId}") {
        const val CHALLENGE_ID = "challengeId"

        fun navigateToRemindersScreen(navController: NavController, challengeId: Int) {
            navController.navigate(RemindersScreen.route.replace("{$CHALLENGE_ID}", challengeId.toString()))
        }
    }

    object ProfileGraph: Routes("profile_graph")
    object RemindersGraph: Routes("reminders_graph")

    object ActiveChallengesScreen : Routes("active_challenges_screen") {
        fun navigateToActiveChallengesScreen(navController: NavController) {
            navController.navigate(ActiveChallengesScreen.route)
        }
    }
}
