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
import com.bangkit.wander.data.request.HotelsRequest
import com.bangkit.wander.presentation.profile.ProfileEmailScreen
import com.bangkit.wander.presentation.profile.ProfileNameScreen
import com.bangkit.wander.presentation.profile.ProfileScreen
import com.bangkit.wander.presentation.saved.PlaceDetailScreen
import com.bangkit.wander.presentation.saved.PlacesListScreen
import com.bangkit.wander.presentation.saved.SavedScreen
import com.bangkit.wander.presentation.search.LocationDetailScreen
import com.bangkit.wander.presentation.search.WanderScreen

class AppRoute {
    companion object {
        const val MAIN = "main"
        const val WANDER = "wander"
        const val MY_PLAN = "my_plan"
        const val FAVORITE = "favorite"
        const val ACCOUNT = "account"

        const val CREATE_PLAN = "my_plan/create"
        const val HOTEL_LIST = "my_plan/create/hotel_list/{hotels_request}"
        const val PLAN_DETAIL = "my_plan/plan_detail/{planId}"
        const val HOTEL_DETAIL = "my_plan/create/hotel_detail"
        const val SUCCESS_CREATE = "my_plan/success_create"

        const val ACCOUNT_NAME = "account/account_name"
        const val ACCOUNT_EMAIL = "account/account_email"

        const val PLACE_LIST = "saved/place_list"
        const val PLACE_DETAIL = "saved/place_detail"

        const val LOCATION_DETAIL = "wander/location_detail"
    }
}

@Composable
fun AppNavBottomBar(navController: NavHostController, mainNavController: NavHostController) {
    NavHost(navController = navController, startDestination = "wander") {
        composable(AppRoute.WANDER) { WanderScreen(mainNavController) }
        composable(AppRoute.MY_PLAN){ MyPlanScreen(mainNavController) }
        composable(AppRoute.FAVORITE) { SavedScreen(mainNavController) }
        composable(AppRoute.ACCOUNT) { ProfileScreen(mainNavController) }
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
                navArgument("planId") { type = NavType.StringType }
            )) { backStackEntry ->
            val planId = backStackEntry.arguments?.getString("planId")
            PlanDetailScreen(navController, planId ?: "")
        }
        composable(AppRoute.HOTEL_DETAIL) { HotelDetailScreen(navController)}
        composable(AppRoute.SUCCESS_CREATE) { SuccessScreen(navController) }
        composable(AppRoute.ACCOUNT_NAME){ ProfileNameScreen(navController)}
        composable(AppRoute.ACCOUNT_EMAIL){ ProfileEmailScreen(navController)}
        composable(AppRoute.PLACE_LIST){ PlacesListScreen(navController)}
        composable(AppRoute.PLACE_DETAIL){ PlaceDetailScreen(navController) }

        composable(AppRoute.LOCATION_DETAIL) { LocationDetailScreen(navController) }
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