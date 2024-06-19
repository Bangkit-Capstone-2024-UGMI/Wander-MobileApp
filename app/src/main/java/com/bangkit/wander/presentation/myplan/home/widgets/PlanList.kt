package com.bangkit.wander.presentation.myplan.home.widgets

import com.bangkit.wander.app.navigation.AppRoute
import com.bangkit.wander.app.theme.AppColor
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bangkit.wander.data.local.TemporaryData
import com.bangkit.wander.presentation.ViewModelFactory
import com.bangkit.wander.presentation.myplan.home.HomePlanViewModel
import kotlinx.coroutines.launch

@Composable
fun PlanList (navController: NavHostController) {
    val context = LocalContext.current
    val viewModel : HomePlanViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )

    val plans by viewModel.planList.observeAsState(emptyList())
    val loading by viewModel.loading.observeAsState(true)

    LaunchedEffect(Unit) {
        viewModel.getPlanList()
    }

    Column {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Plan List", style = TextStyle(fontSize = 20.sp))
            TextButton(
                onClick = { navController.navigate(AppRoute.CREATE_PLAN) },
                colors = ButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = AppColor.PrimaryBlue,
                    disabledContainerColor = Color(0xFFE0E0E0),
                    disabledContentColor = Color(0xFFB0B0B0),
                )
                ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icon" )
                Text(text = "Add New Plan")
            }
        }
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
                            text = "Loading Plan List...",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            plans.isEmpty() -> {
                EmptyStatePlan()
            }

            else -> {
                LazyColumn {
                    items(plans.size) { index ->
                        Box (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        ) {
                            PlanCard(
                                data = plans[index],
                                onClick = {
                                    TemporaryData.planDetail = plans[index]
                                    navController.navigate(AppRoute.PLAN_DETAIL)
                                })
                        }
                    }
                }
            }
        }
    }
}