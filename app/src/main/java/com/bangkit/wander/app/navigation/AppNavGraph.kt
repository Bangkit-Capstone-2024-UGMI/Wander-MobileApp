package com.bangkit.wander.app.navigation

import com.bangkit.wander.presentation.main.MainScreen
import com.bangkit.wander.presentation.myplan.create.CreatePlanScreen
import com.bangkit.wander.presentation.myplan.create.SuccessScreen
import com.bangkit.wander.presentation.myplan.create.hotels.HotelDetailScreen
import com.bangkit.wander.presentation.myplan.create.hotels.HotelListScreen
import com.bangkit.wander.presentation.myplan.home.MyPlanScreen
import com.bangkit.wander.presentation.myplan.home.PlanDetailScreen
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class AppRoute {
    companion object {
        const val MAIN = "main"
        const val WANDER = "wander"
        const val MY_PLAN = "my_plan"
        const val FAVORITE = "favorite"
        const val ACCOUNT = "account"

        const val CREATE_PLAN = "my_plan/create"
        const val HOTEL_LIST = "my_plan/create/hotel_list"
        const val PLAN_DETAIL = "my_plan/plan_detail/{id}"
        const val HOTEL_DETAIL = "my_plan/create/hotel_detail"
        const val SUCCESS_CREATE = "my_plan/success_create"
    }
}

@Composable
fun AppNavGraph(navController: NavHostController, mainNavController: NavHostController) {
    NavHost(navController = navController, startDestination = "wander") {
        composable(AppRoute.WANDER) { DefaultScreen("Wander") }
        composable(AppRoute.MY_PLAN){ MyPlanScreen(mainNavController) }
        composable(AppRoute.FAVORITE) { DefaultScreen("Favorite") }
        composable(AppRoute.ACCOUNT) { DefaultScreen("Account") }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppRoute.MAIN ) {
        composable(AppRoute.MAIN) { MainScreen(mainNavController = navController, navController = rememberNavController()) }
        composable(AppRoute.CREATE_PLAN) { CreatePlanScreen(navController) }
        composable(AppRoute.HOTEL_LIST) { HotelListScreen(navController) }
        composable(
            route = AppRoute.PLAN_DETAIL,
            arguments = listOf(
                navArgument("id") { type = NavType.StringType }
        )) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")
                id?.let {
                    PlanDetailScreen(navController, id)
                }
            }
        composable(AppRoute.HOTEL_DETAIL) { HotelDetailScreen(navController)}
        composable(AppRoute.SUCCESS_CREATE) { SuccessScreen(navController) }
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