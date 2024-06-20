package com.bangkit.wander.presentation.myplan.home

import com.bangkit.wander.R
import com.bangkit.wander.app.widgets.MyTopAppBar
import com.bangkit.wander.presentation.ViewModelFactory
import com.bangkit.wander.presentation.myplan.home.widgets.PlanList
import com.bangkit.wander.presentation.myplan.home.widgets.SearchBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun MyPlanScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel : HomePlanViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val errorMessage by viewModel.errorMessage.observeAsState()

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            scope.launch {
                snackbarHostState.showSnackbar(it)
                viewModel.clearErrorMessage() // Clear the error message after showing the snackbar
            }
        }
    }

    Scaffold(
        topBar = {
            MyTopAppBar(title = "My Plan")
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Image(
                    painter = painterResource(id = R.drawable.banner_plan),
                    contentDescription = "Banner Plan",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 120.dp, start = 16.dp, end = 16.dp)
                ) {
                    Box (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                    ) {
                        SearchBox(navController)
                    }
                    PlanList(navController)
                }
            }
    }
}