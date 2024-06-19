package com.bangkit.wander.presentation.myplan.home

import com.bangkit.wander.app.theme.AppColor
import com.bangkit.wander.app.widgets.MyTextField
import com.bangkit.wander.app.widgets.MyTopAppBar
import com.bangkit.wander.presentation.ViewModelFactory
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bangkit.wander.app.navigation.AppRoute
import com.bangkit.wander.data.local.TemporaryData
import com.bangkit.wander.data.model.Hotel
import com.bangkit.wander.presentation.myplan.create.hotels.widgets.HotelCard


@Composable
fun PlanDetailScreen(
    navController: NavHostController
) {
    val plan = TemporaryData.planDetail

    Scaffold(
        topBar = {
            MyTopAppBar(title = "Plan Detail", showBackButton = true, onBackClick = { navController.popBackStack() })
        },
    ) {
        paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn (
                modifier = Modifier.padding(16.dp)
            ) {
                item {
                    MyTextField(
                        label = "Plan Name",
                        readOnly = true,
                        value = plan?.title?:"Plan Name",
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    MyTextField(
                        label = "Date",
                        value = plan?.date?:"00-00-0000",
                        icon = { Icon(imageVector = Icons.Default.DateRange, contentDescription = "Date" ) },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    MyTextField(
                        label = "Location",
                        value = plan?.city?:"Plan Location",
                        icon = { Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Location" ) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Destinations",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppColor.PrimaryDark
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    val destinationList = plan?.destinations?: emptyList()
                    destinationList.forEach {
                        MyTextField(
                            label = null,
                            placeholder = "Find a destination",
                            readOnly = true,
                            value = it.name,
                            icon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Location" ) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Destinations",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppColor.PrimaryDark
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    plan?.hotel?.let {
                        HotelCard(
                            data = it,
                            onClick = {
                                TemporaryData.hotelDetail = it
                                TemporaryData.sourceHotel = "planDetail"
                                navController.navigate(AppRoute.HOTEL_DETAIL)
                            }
                        )
                    }
                }
            }
        }
    }
}