package academy.bangkit.wander.presentation.myplan.home

import academy.bangkit.wander.app.theme.AppColor
import academy.bangkit.wander.app.widgets.MyTextField
import academy.bangkit.wander.app.widgets.MyTopAppBar
import academy.bangkit.wander.presentation.ViewModelFactory
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
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
fun PlanDetailScreen(
    navController: NavHostController,
    id: String
) {
    val context = LocalContext.current
    val viewModel: HomePlanViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )
    val plan = viewModel.getPlanById(id)
    val planNameText by viewModel.planNameText.observeAsState(initial = plan?.name)
    val dateText by viewModel.planDateText.observeAsState(initial = plan?.date)
    val locationText by viewModel.planLocationText.observeAsState(initial = plan?.location)

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
                        value = planNameText?:"",
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    MyTextField(
                        label = "Date",
                        value = dateText?:"",
                        icon = { Icon(imageVector = Icons.Default.DateRange, contentDescription = "Date" ) },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    MyTextField(
                        label = "Location",
                        value = locationText?:"",
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
//                    Spacer(modifier = Modifier.height(8.dp))
//                    destinationList.forEach { it ->
//                        MyTextField(
//                            label = null,
//                            placeholder = "Find a destination",
//                            value = it,
//                            onValueChange = { viewModel.addDestination(it) },
//                            icon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Location" ) }
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                    }
                }
            }
        }
    }
}