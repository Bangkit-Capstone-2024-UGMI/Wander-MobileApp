package academy.bangkit.wander.presentation.myplan.create.hotels

import academy.bangkit.wander.app.widgets.MyTopAppBar
import academy.bangkit.wander.data.model.Hotel
import academy.bangkit.wander.presentation.myplan.create.hotels.widgets.EmptyStateHotel
import academy.bangkit.wander.presentation.myplan.create.hotels.widgets.HotelCard
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
                .padding(16.dp)
        ) {
            if (true) {
                EmptyStateHotel()
            }
            LazyColumn {

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        HotelCard(
                            data = Hotel(
                                name = "Hotel 1",
                                location = "Location 1",
                                image = "https://asset-a.grid.id/crop/0x0:0x0/945x630/photo/2023/06/18/staycationjpg-20230618013836.jpg",
                                price = "Rp 1.000.000"
                            )
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        HotelCard(
                            data = Hotel(
                                name = "Hotel 2",
                                location = "Location 2",
                                image = "https://asset-a.grid.id/crop/0x0:0x0/945x630/photo/2023/06/18/staycationjpg-20230618013836.jpg",
                                price = "Rp 1.500.000"
                            )
                        )
                    }
                }


            }
        }
        }
    }
}