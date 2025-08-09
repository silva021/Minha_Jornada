package com.silva021.minhajornada.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.silva021.minhajornada.domain.model.ChallengeResult
import com.silva021.minhajornada.domain.model.toDomain
import com.silva021.minhajornada.ui.DatabaseFake
import com.silva021.minhajornada.ui.routes.Routes
import com.silva021.minhajornada.ui.routes.Routes.ChallengeCompletedScreen.STATUS_ID
import com.silva021.minhajornada.ui.routes.Routes.CommunityDetailsScreen
import com.silva021.minhajornada.ui.routes.Routes.CommunityFeedScreen.COMMUNITY_ID
import com.silva021.minhajornada.ui.routes.Routes.ExplorerChallengeDetailsScreen.CHALLENGE_ID
import com.silva021.minhajornada.ui.routes.Routes.UpdateChallengeProgressScreen.navigateToUpdateChallengeProgressScreen
import com.silva021.minhajornada.ui.screens.challenges.actives.ActiveChallengesContent
import com.silva021.minhajornada.ui.screens.challenges.actives.ActiveChallengesScreen
import com.silva021.minhajornada.ui.screens.challenges.completed.ChallengeCompletedScreen
import com.silva021.minhajornada.ui.screens.challenges.create.CreateChallengeScreen
import com.silva021.minhajornada.ui.screens.challenges.mine.ChallengesScreen
import com.silva021.minhajornada.ui.screens.challenges.reminders.RemindersScreen
import com.silva021.minhajornada.ui.screens.challenges.reminders.RemindersViewModel
import com.silva021.minhajornada.ui.screens.challenges.reminders.create.CreateReminderScreen
import com.silva021.minhajornada.ui.screens.challenges.summary.ChallengeSummaryScreen
import com.silva021.minhajornada.ui.screens.challenges.update.UpdateChallengeProgressScreen
import com.silva021.minhajornada.ui.screens.communities.details.CommunityDetailsScreen
import com.silva021.minhajornada.ui.screens.communities.feed.CommunityFeedScreen
import com.silva021.minhajornada.ui.screens.communities.list.CommunitiesScreen
import com.silva021.minhajornada.ui.screens.communities.post.CommunityPostScreen
import com.silva021.minhajornada.ui.screens.communities.post.edit.EditPostScreen
import com.silva021.minhajornada.ui.screens.explorer.challengedetails.ExplorerChallengeDetailsScreen
import com.silva021.minhajornada.ui.screens.explorer.list.ExplorerScreen
import com.silva021.minhajornada.ui.screens.feedback.FeedbackScreen
import com.silva021.minhajornada.ui.screens.help.HelpScreen
import com.silva021.minhajornada.ui.screens.login.LoginScreen
import com.silva021.minhajornada.ui.screens.login.signup.SignUpScreen
import com.silva021.minhajornada.ui.screens.profile.ProfileScreen
import com.silva021.minhajornada.ui.screens.profile.ProfileViewModel
import com.silva021.minhajornada.ui.screens.profile.edit.EditProfileScreen
import com.silva021.minhajornada.ui.screens.welcome.WelcomeScreen
import com.silva021.minhajornada.ui.theme.Palette
import com.silva021.minhajornada.ui.utils.hasNavigationToRoute
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val systemUiController = rememberSystemUiController()
            val currentRoute = currentRoute(navController)

            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = false
                )
            }
            Scaffold(
                containerColor = Palette.backgroundColor,
                bottomBar = {
                    if (currentRoute in listOf(
                            Routes.ChallengesScreen.route,
                            Routes.ProfileScreen.route,
                            Routes.CommunitiesScreen.route,
                            Routes.ExplorerScreen.route,
                        )
                    ) {
                        BottomNavigationBar(
                            currentRoute,
                            navController
                        )
                    }
                }
            ) { padding ->
                val profileViewModel: ProfileViewModel = koinViewModel()
                NavHost(
                    navController = navController,
                    startDestination =
                        if (Firebase.auth.currentUser != null)
                            Routes.ChallengesScreen.route
                        else
                            Routes.WelcomeScreen.route,
                    modifier = Modifier.padding(padding)
                ) {
                    composable(Routes.WelcomeScreen.route) {
                        WelcomeScreen(
                            onGetStartedClicked = {
                                Routes.LoginScreen.navigateToLoginScreen(navController)
                            }
                        )
                    }

                    navigation(
                        startDestination = Routes.ProfileScreen.route,
                        route = Routes.ProfileGraph.route
                    ) {

                        composable(Routes.ProfileScreen.route) { backStackEntry ->
                            ProfileScreen(
                                viewModel = profileViewModel,
                                onContactUsClick = {
                                    Routes.FeedbackScreen.navigateToFeedbackScreen(navController)
                                },
                                onHelpClick = {
                                    Routes.HelpScreen.navigateToHelpScreen(navController)
                                },
                                onEditProfileClick = {
                                    Routes.EditProfileScreen.navigateToEditProfileScreen(
                                        navController
                                    )
                                },
                                onRemindersClick = {
                                    Routes.ActiveChallengesScreen.navigateToActiveChallengesScreen(
                                        navController
                                    )
                                }
                            )
                        }

                        composable(Routes.EditProfileScreen.route) { backStackEntry ->
                            EditProfileScreen(
                                profileViewModel,
                                onEditProfilePicture = {
                                    // Handle profile picture edit
                                },
                                onSaveProfileChanges = {

                                },
                                onBackPressed = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }

                    composable(
                        Routes.ChallengesScreen.route,
                    ) {
                        ChallengesScreen(
                            onCreateChallenge = {
                                Routes.CreateChallengesScreen.navigateToCreateChallengesScreen(
                                    navController
                                )
                            },
                            onUpdateChallengeProgress = {
                                navigateToUpdateChallengeProgressScreen(navController, it)
                            },
                            onSummaryChallengeClick = {
                                Routes.ChallengeSummaryScreen.navigateToChallengeSummaryScreen(
                                    navController,
                                    it
                                )
                            }
                        )
                    }

                    composable(Routes.CreateChallengesScreen.route) {
                        CreateChallengeScreen(
                            onBackPressed = {
                                Routes.ChallengesScreen.navigateToChallengesScreen(
                                    navController
                                )
                            }
                        )
                    }
                    composable(
                        route = Routes.UpdateChallengeProgressScreen.route,
                        arguments = listOf(
                            navArgument(Routes.UpdateChallengeProgressScreen.CHALLENGE_ID) {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        UpdateChallengeProgressScreen(
                            challengeId = it.arguments?.getString(Routes.UpdateChallengeProgressScreen.CHALLENGE_ID) ?: "",
                            onCompleteChallenge = {
                                Routes.ChallengeCompletedScreen.navigateToChallengeCompletedScreen(
                                    navController,
                                    it
                                )
                            },
                            onCompletedDay = {
                                Routes.ChallengeCompletedScreen.navigateToChallengeCompletedScreen(
                                    navController,
                                    it
                                )
                            },
                            onBackPressed = {
                                navController.popBackStack(
                                    Routes.ChallengesScreen.route,
                                    inclusive = false
                                )
                            }
                        )
                    }
                    composable(Routes.CommunitiesScreen.route) {
                        CommunitiesScreen(
                            onCommunityClick = {
                                CommunityDetailsScreen.navigateToCommunityDetailsScreen(
                                    navController,
                                    it.id
                                )
                            },
                            onMineCommunityClick = {
                                Routes.CommunityFeedScreen.navigateToCommunityFeedScreen(
                                    navController,
                                    it.id
                                )
                            }
                        )
                    }
                    composable(Routes.FeedbackScreen.route) {
                        FeedbackScreen(onBackPressed = { navController.popBackStack() })
                    }

                    composable(Routes.HelpScreen.route) {
                        HelpScreen(onBackPressed = { navController.popBackStack() })
                    }

                    composable(Routes.ExplorerScreen.route) {
                        ExplorerScreen(
                            onChallengeClick = {
                                Routes.ExplorerChallengeDetailsScreen.navigateToExplorerChallengeDetailsScreen(
                                    navController,
                                    challengeId = it.id
                                )
                            }
                        )
                    }

                    composable(
                        route = Routes.ExplorerChallengeDetailsScreen.route,
                        arguments = listOf(
                            navArgument(CHALLENGE_ID) {
                                type = NavType.StringType
                            }
                        )
                    ) { backStackEntry ->
                        val challengeId =
                            backStackEntry.arguments?.getString(CHALLENGE_ID).orEmpty()

                        ExplorerChallengeDetailsScreen(
//                            challenge = DatabaseFake.publicChallenges.find { it.id == challengeId }!!,
                            challenge = DatabaseFake.publicChallenges.find { it.id == challengeId }!!
                                .toDomain(),
                            onBackPressed = {
                                navController.popBackStack(
                                    Routes.ExplorerScreen.route,
                                    inclusive = false
                                )
                            }
                        )
                    }

                    composable(
                        route = CommunityDetailsScreen.route,
                        arguments = listOf(
                            navArgument(CommunityDetailsScreen.COMMUNITY_ID) {
                                type = NavType.StringType
                            }
                        )
                    ) { backStackEntry ->
                        val communityId =
                            backStackEntry.arguments?.getString(CommunityDetailsScreen.COMMUNITY_ID)
                                .orEmpty()
                        CommunityDetailsScreen(
                            communityId = communityId,
                            onBackPressed = {
                                navController.popBackStack(
                                    Routes.CommunitiesScreen.route,
                                    inclusive = false
                                )
                            }
                        )
                    }

                    composable(
                        route = Routes.CommunityFeedScreen.route,
                        arguments = listOf(
                            navArgument(COMMUNITY_ID) {
                                type = NavType.StringType
                            }
                        )
                    ) { backStackEntry ->
                        val communityId =
                            backStackEntry.arguments?.getString(CommunityDetailsScreen.COMMUNITY_ID)
                                .orEmpty()
                        CommunityFeedScreen(
                            communityId = communityId,
                            onBackPressed = {
                                navController.popBackStack(
                                    Routes.CommunitiesScreen.route,
                                    inclusive = false
                                )
                            },
                            onClickPost = {
                                Routes.CommunityPostScreen.navigateToCommunityPostScreen(
                                    navController,
                                    it.id
                                )
                            },
                            onEditPost = {
                                Routes.EditPostScreen.navigateToEditPostScreen(
                                    navController,
                                    it.id
                                )
                            }
                        )
                    }

                    composable(
                        route = Routes.CommunityPostScreen.route,
                        arguments = listOf(
                            navArgument(Routes.CommunityPostScreen.POST_ID) {
                                type = NavType.StringType
                            }
                        )
                    ) { backStackEntry ->
                        val postId =
                            backStackEntry.arguments?.getString(Routes.CommunityPostScreen.POST_ID)
                                .orEmpty()
                        CommunityPostScreen(
                            postId = postId,
                            onBackPressed = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(
                        route = Routes.ChallengeSummaryScreen.route,
                        arguments = listOf(
                            navArgument(Routes.ChallengeSummaryScreen.CHALLENGE_ID) {
                                type = NavType.StringType
                            }
                        )
                    ) { backStackEntry ->
                        val challengeId =
                            backStackEntry.arguments?.getString(Routes.ChallengeSummaryScreen.CHALLENGE_ID)
                                .orEmpty()
                        ChallengeSummaryScreen(
                            challengeId = challengeId,
                            onBackPressed = {
                                navController.popBackStack(
                                    Routes.ChallengesScreen.route,
                                    inclusive = false
                                )
                            }
                        )
                    }

                    composable(
                        Routes.ChallengeCompletedScreen.route,
                        arguments = listOf(
                            navArgument(STATUS_ID) { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val status = ChallengeResult.valueOf(
                            backStackEntry.arguments?.getString(STATUS_ID).orEmpty()
                        )
                        ChallengeCompletedScreen(
                            challengeResult = status,
                            onBackPressed = {
                                navController.popBackStack(
                                    Routes.ChallengesScreen.route,
                                    inclusive = false
                                )
                            }
                        )
                    }

                    composable(Routes.SignUpScreen.route) {
                        SignUpScreen(
                            onNavigateChallenge = {
                                Routes.ChallengesScreen.navigateToChallengesScreen(navController)
                            },
                            onBackPressed = {
                                navController.popBackStack(
                                    Routes.LoginScreen.route,
                                    inclusive = false
                                )
                            }
                        )
                    }

                    composable(Routes.LoginScreen.route) {
                        LoginScreen(
                            onLoginSuccess = {
                                Routes.ChallengesScreen.navigateToChallengesScreen(navController)
                            },
                            onSignUp = {
                                Routes.SignUpScreen.navigateToSignUpScreen(navController)
                            }
                        )
                    }

                    composable(
                        route = Routes.EditPostScreen.route,
                        arguments = listOf(
                            navArgument(Routes.EditPostScreen.POST_ID) {
                                type = NavType.StringType
                            }
                        )
                    ) { backStackEntry ->
                        val postId =
                            backStackEntry.arguments?.getString(Routes.EditPostScreen.POST_ID)
                                .orEmpty()
                        EditPostScreen(
                            postId = postId,
                            onBackPressed = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(Routes.ActiveChallengesScreen.route) {
                        ActiveChallengesScreen(
                            onChallengeClick = {
                                Routes.RemindersScreen.navigateToRemindersScreen(
                                    navController,
                                    it.id
                                )
                            },
                            onBackPressed = {
                                navController.popBackStack()
                            }
                        )
                    }
                    navigation(
                        startDestination = Routes.RemindersScreen.route,
                        route = Routes.RemindersGraph.route
                    ) {
                        composable(
                            route = Routes.RemindersScreen.route,
                            arguments = listOf(
                                navArgument(Routes.RemindersScreen.CHALLENGE_ID) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val challengeId = it.arguments?.getString(Routes.RemindersScreen.CHALLENGE_ID)
                            RemindersScreen(
                                challengeId = challengeId.orEmpty(),
                                onBackPressed = {
                                    navController.popBackStack(
                                        Routes.ActiveChallengesScreen.route,
                                        inclusive = false
                                    )
                                },
                                onEditReminder = { reminderId ->
                                    Routes.CreateReminderScreen.navigateToCreateReminderScreen(
                                        navController,
                                        reminderId = reminderId,
                                        challengeId = challengeId.orEmpty()
                                    )
                                },
                                onAddReminderClick = {
                                    Routes.CreateReminderScreen.navigateToCreateReminderScreen(
                                        navController = navController,
                                        reminderId = null,
                                        challengeId = challengeId.orEmpty()
                                    )
                                },
                            )
                        }

                        composable(
                            route = Routes.CreateReminderScreen.route,
                            arguments = listOf(
                                navArgument(Routes.CreateReminderScreen.REMINDER_ID) {
                                    type = NavType.StringType
                                },
                                navArgument(Routes.CreateReminderScreen.REMINDER_ID) {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            val reminderId =
                                backStackEntry.arguments?.getString(
                                    Routes.CreateReminderScreen.REMINDER_ID
                                )

                            val challengeId =
                                backStackEntry.arguments?.getString(
                                    Routes.CreateReminderScreen.CHALLENGE_ID
                                ).orEmpty()

                            CreateReminderScreen(
                                reminderId = reminderId,
                                challengeId = challengeId,
                                onBackPressed = {
                                    navController.popBackStack(
                                        Routes.RemindersScreen.route,
                                        inclusive = false
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    navController: NavController,
) {
    NavigationBar(
        containerColor = Palette.accentColor,
    ) {
        NavigationBarItem(
            currentRoute = currentRoute,
            routeOfTheItem = Routes.ChallengesScreen.route,
            icon = Icons.Default.Home,
            label = "Início",
            onClick = {
                Routes.ChallengesScreen.navigateToChallengesScreen(navController)
            }
        )
        NavigationBarItem(
            currentRoute = currentRoute,
            routeOfTheItem = Routes.ExplorerScreen.route,
            icon = Icons.Default.Explore,
            label = "Explorar",
            onClick = {
                Routes.ExplorerScreen.navigateToExplorerScreen(navController)
            }
        )
        NavigationBarItem(
            currentRoute = currentRoute,
            routeOfTheItem = Routes.CommunitiesScreen.route,
            icon = Icons.Default.People,
            label = "Comunidades",
            onClick = {
                Routes.CommunitiesScreen.navigateToCommunitiesScreen(navController)
            }
        )
        NavigationBarItem(
            currentRoute = currentRoute,
            routeOfTheItem = Routes.ProfileScreen.route,
            icon = Icons.Default.Person,
            label = "Perfil",
            onClick = {
                Routes.ProfileScreen.navigateToProfileScreen(navController)
            }
        )
    }
}

@Composable
fun RowScope.NavigationBarItem(
    currentRoute: String,
    routeOfTheItem: String,
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
) {
    val isSelected = currentRoute == routeOfTheItem
    val color = if (isSelected) Palette.primaryColor else Palette.textSecondary
    NavigationBarItem(
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color
            )
        },
        label = {
            Text(
                label,
                color = color
            )
        },
        selected = isSelected,
        onClick = {
            if (currentRoute.hasNavigationToRoute(routeOfTheItem))
                onClick.invoke()

        },
        colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
    )
}

@Composable
fun currentRoute(navController: NavHostController): String {
    return navController.currentBackStackEntryAsState().value?.destination?.route.orEmpty()
}