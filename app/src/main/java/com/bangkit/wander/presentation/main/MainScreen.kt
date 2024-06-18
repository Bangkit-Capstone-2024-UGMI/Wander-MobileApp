package com.bangkit.wander.presentation.main

import com.bangkit.wander.app.navigation.AppNavGraph
import com.bangkit.wander.app.navigation.AppRoute
import com.bangkit.wander.app.widgets.BottomNavigationItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun MainScreen(navController: NavHostController, mainNavController: NavHostController) {


        val items = listOf(
            BottomNavigationItem(
                title = "Wander",
                selectedIcon = Icons.Filled.Place,
                unselectedIcon = Icons.Outlined.Place,
                hasNews = false,
                routeName = AppRoute.WANDER
            ),
            BottomNavigationItem(
                title = "My Plan",
                selectedIcon = Icons.Filled.Create,
                unselectedIcon = Icons.Outlined.Create,
                hasNews = false,
                routeName = AppRoute.MY_PLAN
            ),
            BottomNavigationItem(
                title = "Favorite",
                selectedIcon = Icons.Filled.Favorite,
                unselectedIcon = Icons.Outlined.FavoriteBorder,
                hasNews = false,
                routeName = AppRoute.FAVORITE
            ),
            BottomNavigationItem(
                title = "Account",
                selectedIcon = Icons.Filled.Person,
                unselectedIcon = Icons.Outlined.Person,
                hasNews = false,
                routeName = AppRoute.ACCOUNT
            ),
        )
        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }
        Scaffold(
                bottomBar = {
                    NavigationBar {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedItemIndex == index,
                                onClick = {
                                    selectedItemIndex = index
                                    navController.popBackStack()
                                    navController.navigate(item.routeName)
                                },
                                label = {
                                    Text(text = item.title)
                                },
                                alwaysShowLabel = false,
                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if(item.badgeCount != null) {
                                                Badge {
                                                    Text(text = item.badgeCount.toString())
                                                }
                                            } else if(item.hasNews) {
                                                Badge()
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (index == selectedItemIndex) {
                                                item.selectedIcon
                                            } else item.unselectedIcon,
                                            contentDescription = item.title
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            ) {
                    paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    AppNavGraph(navController = navController, mainNavController = mainNavController)
                }
            }
        }

