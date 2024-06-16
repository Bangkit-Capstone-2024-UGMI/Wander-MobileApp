package academy.bangkit.wander.presentation.myplan.create

import academy.bangkit.wander.app.navigation.AppRoute
import academy.bangkit.wander.app.theme.AppColor
import academy.bangkit.wander.app.widgets.MyButton
import academy.bangkit.wander.app.widgets.MyTextField
import academy.bangkit.wander.app.widgets.MyTopAppBar
import academy.bangkit.wander.presentation.ViewModelFactory
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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

@Composable
fun CreatePlanScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: CreatePlanViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )

    val planNameText by viewModel.planNameText.observeAsState(initial = "")
    val dateText by viewModel.dateText.observeAsState(initial = "")
    val locationText by viewModel.locationText.observeAsState(initial = "")
    val destinationList by viewModel.destinationList.observeAsState(initial = listOf())

    Scaffold (
        topBar = {
            MyTopAppBar(
                title = "Create a New Plan",
                showBackButton = true,
                onBackClick = { navController.popBackStack() })
        },
        bottomBar = {
            Box(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                MyButton(
                    text = "Find Hotels",
                    onClick = {navController.navigate(AppRoute.HOTEL_LIST)}
                )
            }
        }
    ){
        paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                LazyColumn (
                    modifier = Modifier.padding(16.dp)
                ) {
                    item {
                        MyTextField(
                            label = "Plan Name",
                            placeholder = "Enter your plan name",
                            value = planNameText,
                            onValueChange = { viewModel.onPlanNameTextChanged(it) }
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        MyTextField(
                            label = "Date",
                            placeholder = "Enter your plan date",
                            value = dateText,
                            icon = { Icon(imageVector = Icons.Default.DateRange, contentDescription = "Date" ) },
                            onValueChange = { viewModel.onDateTextChanged(it) }
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        MyTextField(
                            label = "Location",
                            placeholder = "Enter your plan location",
                            value = locationText,
                            onValueChange = { viewModel.onLocationTextChanged(it) },
                            icon = { Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Location" )}
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
                        destinationList.forEach { it ->
                            MyTextField(
                                label = null,
                                placeholder = "Find a destination",
                                value = it,
                                onValueChange = { viewModel.addDestination(it) },
                                icon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Location" )}
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        MyButton(
                            text = "Add a Destination",
                            onClick = { viewModel.addDestination("")},
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add Icon"
                                )
                            },
                            isSecondary = true
                        )
                    }
                }
            }
    }
}