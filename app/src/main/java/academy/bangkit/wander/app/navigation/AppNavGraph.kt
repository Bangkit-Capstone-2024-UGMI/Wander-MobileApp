package academy.bangkit.wander.app.navigation

import academy.bangkit.wander.presentation.main.MainScreen
import academy.bangkit.wander.presentation.myplan.create.CreatePlanScreen
import academy.bangkit.wander.presentation.myplan.home.MyPlanScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class AppRoute {
    companion object {
        const val MAIN = "main"
        const val WANDER = "wander"
        const val MY_PLAN = "my_plan"
        const val FAVORITE = "favorite"
        const val ACCOUNT = "account"

        const val CREATE_PLAN = "my_plan/create"
    }
}

@Composable
fun AppNavGraph(navController: NavHostController, mainNavController: NavHostController) {
    NavHost(navController = navController, startDestination = "wander") {
        composable(AppRoute.WANDER) { DefaultScreen("Wander") }
        composable(AppRoute.MY_PLAN){ MyPlanScreen(mainNavController) }
        composable(AppRoute.FAVORITE) { DefaultScreen("Favorite") }
        composable(AppRoute.ACCOUNT) { DefaultScreen("Account") }
        composable(AppRoute.CREATE_PLAN) {CreatePlanScreen(mainNavController)}
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable(AppRoute.MAIN) { MainScreen(mainNavController = navController, navController = rememberNavController()) }
        composable(AppRoute.CREATE_PLAN) {CreatePlanScreen(navController)}
    }
}

@Composable
fun DefaultScreen(
    title: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title)
        }
    }
}