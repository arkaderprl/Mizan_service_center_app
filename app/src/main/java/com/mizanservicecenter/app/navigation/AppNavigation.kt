package com.mizanservicecenter.app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mizanservicecenter.app.ui.screens.AboutScreen
import com.mizanservicecenter.app.ui.screens.PrivacyPolicyScreen
import com.mizanservicecenter.app.ui.screens.SettingsScreen
import com.mizanservicecenter.app.ui.screens.WebViewScreen
import com.mizanservicecenter.app.util.BiometricHelper

@Composable
fun AppNavigation(activity: FragmentActivity) {
    val navController = rememberNavController()
    var isAuthenticated by remember { mutableStateOf(false) }
    var authError by remember { mutableStateOf<String?>(null) }

    val biometricEnabled = false // Ideally from SharedPreferences

    LaunchedEffect(Unit) {
        if (biometricEnabled && BiometricHelper.isBiometricAvailable(activity)) {
            BiometricHelper.authenticate(
                activity = activity,
                onSuccess = { isAuthenticated = true },
                onError = { authError = it }
            )
        } else {
            isAuthenticated = true
        }
    }

    if (isAuthenticated) {
        NavHost(
            navController = navController,
            startDestination = "splash",
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(300)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(300)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(300)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(300)) }
        ) {
            composable("splash") {
                com.mizanservicecenter.app.ui.screens.AnimatedSplashScreen(navController = navController)
            }
            composable("home") {
                WebViewScreen(url = "https://mizanservicecenter.store", navController = navController)
            }
            composable("settings") {
                SettingsScreen(navController = navController)
            }
            composable("about") {
                AboutScreen(navController = navController)
            }
            composable("privacy") {
                PrivacyPolicyScreen(navController = navController)
            }
        }
    }
}
