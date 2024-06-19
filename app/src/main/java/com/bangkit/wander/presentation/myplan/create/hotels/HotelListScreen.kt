package com.bangkit.wander.presentation.myplan.create.hotels

import com.bangkit.wander.app.navigation.AppRoute
import com.bangkit.wander.app.widgets.MyTopAppBar
import com.bangkit.wander.presentation.myplan.create.hotels.widgets.EmptyStateHotel
import com.bangkit.wander.presentation.myplan.create.hotels.widgets.HotelCard
import com.bangkit.wander.presentation.myplan.create.hotels.widgets.InfoSection
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bangkit.wander.data.local.TemporaryData
import com.bangkit.wander.data.request.HotelsRequest
import com.bangkit.wander.presentation.ViewModelFactory
import com.bangkit.wander.presentation.myplan.create.CreatePlanViewModel
import kotlinx.coroutines.launch

@Composable
fun HotelListScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val viewModel: CreatePlanViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )
    val hotels by viewModel.hotels.observeAsState(emptyList())
    val loading by viewModel.loading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        TemporaryData.hotelsRequest?.let {
            viewModel.fetchHotels(it)
        }
    }

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            scope.launch {
                snackbarHostState.showSnackbar(it)
                viewModel.clearErrorMessage() // Clear the error message after showing the snackbar
            }
        }
    }

    Scaffold (
        topBar = {
            MyTopAppBar(title = "Select a Hotel", showBackButton = true, onBackClick = { navController.popBackStack() })
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        paddingValues -> Box(modifier = Modifier.padding(paddingValues)
        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            when {
                loading -> {
                    Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                hotels.isEmpty() -> {
                    EmptyStateHotel()
                }
                else -> {
                        InfoSection()
                        Spacer(modifier = Modifier.padding(8.dp))
                        LazyColumn {
                            items(hotels) { hotel ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp)
                                ) {
                                    HotelCard(
                                        data = hotel,
                                        onClick = { navController.navigate(AppRoute.HOTEL_DETAIL) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}