package com.sourav.multicontactpicker.navGraph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.sourav.contact_picker.view.ContactPickerScreen
import com.sourav.multicontactpicker.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    startLocation: String
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startLocation
    ) {

        composable(
            route = NavigationItem.ContactPickerScreen.route
        ) {
            ContactPickerScreen(
                navigateTo = {
                    navController.navigate(it)
                },
                navController
            )
        }

        composable(
            route = NavigationItem.HomeScreen.route
        ) {
            HomeScreen(
                navigateTo = {
                    navController.navigate(it)
                },
                navController
            )
        }

    }
}
