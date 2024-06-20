package com.bangkit.wander.presentation.myplan.home

import com.bangkit.wander.app.theme.AppColor
import com.bangkit.wander.app.widgets.MyTextField
import com.bangkit.wander.app.widgets.MyTopAppBar
import com.bangkit.wander.presentation.ViewModelFactory
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bangkit.wander.app.navigation.AppRoute
import com.bangkit.wander.data.local.TemporaryData
import com.bangkit.wander.data.model.Hotel
import com.bangkit.wander.presentation.myplan.create.hotels.widgets.HotelCard
import kotlinx.coroutines.launch


@Composable
fun PlanDetailScreen(
    navController: NavHostController,
    planId: String
) {
    val context = LocalContext.current
    val viewModel : HomePlanViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )

    val plan by viewModel.planDetail.observeAsState(null)
    val loading by viewModel.loading.observeAsState(true)

    LaunchedEffect(Unit) {
        viewModel.getPlanById(planId)
    }

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
            MyTopAppBar(title = "Plan Detail", showBackButton = true, onBackClick = { navController.popBackStack() })
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when {
                loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(), contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.padding(8.dp))
                            Text(
                                text = "Loading...",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        item {
                            MyTextField(
                                label = "Plan Name",
                                readOnly = true,
                                value = plan?.title ?: "Plan Name",
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            MyTextField(
                                label = "Date",
                                readOnly = true,
                                value = plan?.date ?: "00-00-0000",
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = "Date"
                                    )
                                },
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            MyTextField(
                                label = "Location",
                                readOnly = true,
                                value = plan?.city ?: "Plan Location",
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = "Location"
                                    )
                                }
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
                            val destinationList = plan?.destinations ?: emptyList()
                            destinationList.forEach {
                                MyTextField(
                                    label = null,
                                    placeholder = "Find a destination",
                                    readOnly = true,
                                    value = it.name,
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Place,
                                            contentDescription = "Destination"
                                        )
                                    }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            plan?.hotel?.let {
                                Text(
                                    text = "Hotel",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = AppColor.PrimaryDark
                                    )
                                )
                                Spacer(modifier = Modifier.height(8.dp))
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
    }
}