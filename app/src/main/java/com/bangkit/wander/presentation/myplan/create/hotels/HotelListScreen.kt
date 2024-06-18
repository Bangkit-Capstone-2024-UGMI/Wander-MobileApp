package com.bangkit.wander.presentation.myplan.create.hotels

import com.bangkit.wander.app.navigation.AppRoute
import com.bangkit.wander.app.widgets.MyTopAppBar
import com.bangkit.wander.data.model.Hotel
import com.bangkit.wander.presentation.myplan.create.hotels.widgets.EmptyStateHotel
import com.bangkit.wander.presentation.myplan.create.hotels.widgets.HotelCard
import com.bangkit.wander.presentation.myplan.create.hotels.widgets.InfoSection
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HotelListScreen(
    navController: NavHostController
) {
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
            if (false) {
                EmptyStateHotel()
            }
            InfoSection()
            Spacer(modifier = Modifier.padding(8.dp))
            LazyColumn {

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        HotelCard(
                            data = Hotel(
                                "Hotel 1",
                                "Bali, Indonesia",
                                4.5,
                                4,
                                123.123,
                                1.9,
                                1.9,
                                "123"
                            ),
                            onClick = { navController.navigate(AppRoute.HOTEL_DETAIL)}
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        HotelCard(
                            data = Hotel(
                                "Hotel 1",
                                "Bali, Indonesia",
                                4.5,
                                4,
                                123.123,
                                1.9,
                                1.9,
                                "123"
                            ),
                            onClick = { navController.navigate(AppRoute.HOTEL_DETAIL)}
                        )
                    }
                }


            }
        }
        }
    }
}