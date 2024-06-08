package academy.bangkit.wander.presentation.main

import academy.bangkit.wander.app.navigation.AppNavGraph
import academy.bangkit.wander.app.navigation.AppRoute
import academy.bangkit.wander.app.theme.M3BottomNavigationTheme
import academy.bangkit.wander.app.widgets.BottomNavigationItem
import academy.bangkit.wander.presentation.ViewModelFactory
import academy.bangkit.wander.presentation.login.LoginActivity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance()
    }
    override fun onStart() {
        super.onStart()
        viewModel.checkCurrentUser()
    }

    private fun setupAction() {
        viewModel.currentUser.observe(this) { user ->
            if (user == null) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setupAction()
        setContent {
            M3BottomNavigationTheme {
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
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            NavigationBar {
                                items.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            selectedItemIndex = index
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
                                AppNavGraph(navController = navController)
                            }
                    }
                }
            }
        }
    }
}