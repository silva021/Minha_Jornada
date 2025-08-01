package com.silva021.minhajornada

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.silva021.minhajornada.ui.routes.Routes
import com.silva021.minhajornada.ui.routes.Routes.UpdateChallengeProgressScreen.navigateToUpdateChallengeProgressScreen
import com.silva021.minhajornada.ui.screens.challenges.mine.ChallengesScreen
import com.silva021.minhajornada.ui.screens.challenges.create.CreateChallengesScreen
import com.silva021.minhajornada.ui.screens.challenges.update.UpdateChallengeProgressScreen
import com.silva021.minhajornada.ui.screens.communities.CommunitiesScreen
import com.silva021.minhajornada.ui.screens.feedback.FeedbackScreen
import com.silva021.minhajornada.ui.screens.help.HelpScreen
import com.silva021.minhajornada.ui.screens.profile.ProfileScreen
import com.silva021.minhajornada.ui.screens.welcome.WelcomeScreen
import com.silva021.minhajornada.ui.theme.Palette

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentRoute = currentRoute(navController)

            Scaffold(
                bottomBar = {
                    if (currentRoute in listOf(
                            Routes.ChallengesScreen.route,
                            Routes.ProfileScreen.route,
                            Routes.CommunitiesScreen.route,
                        )
                    ) {
                        BottomNavigationBar(
                            currentRoute,
                            navController
                        )
                    }
                }
            ) { padding ->
                NavHost(
                    navController = navController,
                    startDestination = Routes.CommunitiesScreen.route,
                    modifier = Modifier.padding(padding)
                ) {
                    composable(Routes.WelcomeScreen.route) {
                        WelcomeScreen(
                            onGetStartedClicked = {
                                Routes.ChallengesScreen.navigateToChallengesScreen(navController)
                            }
                        )
                    }
                    composable(Routes.ChallengesScreen.route) {
                        ChallengesScreen(
                            onCreateChallenge = {
                                Routes.CreateChallengesScreen.navigateToCreateChallengesScreen(
                                    navController
                                )
                            },
                            onUpdateChallengeProgress = {
                                navigateToUpdateChallengeProgressScreen(navController)
                            }
                        )
                    }
                    composable(Routes.ProfileScreen.route) {
                        ProfileScreen(
                            onContactUsClick = {
                                Routes.FeedbackScreen.navigateToFeedbackScreen(navController)
                            },
                            onHelpClick = {
                                Routes.HelpScreen.navigateToHelpScreen(navController)
                            }
                        )
                    }
                    composable(Routes.CreateChallengesScreen.route) {
                        CreateChallengesScreen(
                            onBackPressed = {
                                navController.popBackStack(
                                    Routes.ChallengesScreen.route,
                                    inclusive = false
                                )
                            }
                        )
                    }
                    composable(Routes.UpdateChallengeProgressScreen.route) {
                        UpdateChallengeProgressScreen(
                            onBackPressed = {
                                navController.popBackStack(
                                    Routes.ChallengesScreen.route,
                                    inclusive = false
                                )
                            }
                        )
                    }
                    composable(Routes.CommunitiesScreen.route) { CommunitiesScreen() }
                    composable(Routes.FeedbackScreen.route) {
                        FeedbackScreen(onBackPressed = { navController.popBackStack() })
                    }

                    composable(Routes.HelpScreen.route) {
                        HelpScreen(onBackPressed = { navController.popBackStack() })
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
            label = "Home",
            onClick = {
                Routes.ChallengesScreen.navigateToChallengesScreen(navController)
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
        onClick = onClick,
        colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
    )
}

@Composable
fun currentRoute(navController: NavHostController): String {
    return navController.currentBackStackEntryAsState().value?.destination?.route.orEmpty()
}