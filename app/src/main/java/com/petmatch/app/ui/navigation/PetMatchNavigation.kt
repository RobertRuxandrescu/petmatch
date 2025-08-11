package com.petmatch.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.petmatch.app.ui.dashboard.DashboardScreen
import com.petmatch.app.ui.dashboard.DashboardViewModel
import com.petmatch.app.ui.login.LoginScreen
import com.petmatch.app.ui.login.LoginViewModel
import com.petmatch.app.ui.splash.SplashScreen
import com.petmatch.app.ui.splash.SplashViewModel

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Dashboard : Screen("dashboard")
}

@Composable
fun PetMatchNavigation(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Splash.route) {
            val splashViewModel: SplashViewModel = hiltViewModel()
            
            SplashScreen(
                viewModel = splashViewModel,
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Login.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            
            LoginScreen(
                viewModel = loginViewModel,
                onNavigateToDashboard = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onLaunchGoogleSignIn = {
                    // This is handled by the ViewModel side effect
                },
                onShowToast = { message ->
                    // TODO: Show toast message
                }
            )
        }
        
        composable(Screen.Dashboard.route) {
            val dashboardViewModel: DashboardViewModel = hiltViewModel()
            
            DashboardScreen(
                viewModel = dashboardViewModel,
                onNavigateToPostDetail = { post ->
                    // TODO: Navigate to post detail
                },
                onNavigateToAddPost = {
                    // TODO: Navigate to add post
                },
                onShowToast = { message ->
                    // TODO: Show toast message
                }
            )
        }
    }
}
