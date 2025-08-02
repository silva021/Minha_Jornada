package com.silva021.minhajornada.ui.routes

import androidx.navigation.NavController

sealed class Routes(val route: String) {
    fun popBackStack(navController: NavController) {
        navController.popBackStack()
    }

    object ProfileScreen : Routes("profile_screen") {
        fun navigateToProfileScreen(navController: NavController) {
            navController.navigate(ProfileScreen.route)
        }
    }

    object ChallengesScreen : Routes("challenges_screen") {
        fun navigateToChallengesScreen(navController: NavController) {
            navController.navigate(ChallengesScreen.route)
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
            navController.navigate(CommunitiesScreen.route)
        }
    }

    object ExplorerScreen : Routes("explorer_screen") {
        fun navigateToExplorerScreen(navController: NavController) {
            navController.navigate(ExplorerScreen.route)
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

    object WelcomeScreen : Routes("welcome_screen")
}
