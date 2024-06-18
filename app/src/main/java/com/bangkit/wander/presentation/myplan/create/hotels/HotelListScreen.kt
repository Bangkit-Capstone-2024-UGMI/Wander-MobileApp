package com.bangkit.wander.presentation.myplan.create.hotels

import com.bangkit.wander.app.navigation.AppRoute
import com.bangkit.wander.app.widgets.MyTopAppBar
import com.bangkit.wander.presentation.myplan.create.hotels.widgets.EmptyStateHotel
import com.bangkit.wander.presentation.myplan.create.hotels.widgets.HotelCard
import com.bangkit.wander.presentation.myplan.create.hotels.widgets.InfoSection
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bangkit.wander.presentation.ViewModelFactory
import com.bangkit.wander.presentation.myplan.create.CreatePlanViewModel

@Composable
fun HotelListScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val viewModel: CreatePlanViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )
    val hotels by viewModel.hotels.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchHotels()
    }

    Scaffold (
        topBar = {
            MyTopAppBar(title = "Select a Hotel", showBackButton = true, onBackClick = { navController.popBackStack() })
        }
    ) {
        paddingValues -> Box(modifier = Modifier.padding(paddingValues)
        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            if (hotels.isEmpty()) {
                EmptyStateHotel()
            } else {
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