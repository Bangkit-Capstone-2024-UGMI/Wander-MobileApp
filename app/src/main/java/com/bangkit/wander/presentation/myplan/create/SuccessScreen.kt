package com.bangkit.wander.presentation.myplan.create

import com.bangkit.wander.R
import com.bangkit.wander.app.navigation.AppRoute
import com.bangkit.wander.app.widgets.MyButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bangkit.wander.data.local.TemporaryData
import com.bangkit.wander.presentation.ViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun SuccessScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val viewModel: CreatePlanViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )
    val loading by viewModel.loading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        TemporaryData.newPlan?.let {
            viewModel.createPlan(it)
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

    Scaffold {
        paddingValues ->
            Box(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
                                        text = "AI is searching for\nthe best hotels near your destinations...",
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    else -> {
                    Box(
                        modifier = Modifier
                            .width(LocalConfiguration.current.screenWidthDp.dp / 2)
                            .aspectRatio(1f)

                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.success_state_plan
                            ),
                            contentDescription = "Success State Plan",
                            modifier = Modifier.fillMaxSize(),

                            )
                    }
                    Text(
                        text = "Your vacation plan is ready!",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xCC1D5D9B),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Let the adventure begin.",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xCC1D5D9B),
                            textAlign = TextAlign.Center,
                        )
                    )
                    Spacer(modifier = Modifier.padding(16.dp))
                    Box(
                        modifier = Modifier.padding(horizontal = LocalConfiguration.current.screenWidthDp.dp / 4)
                    ) {
                        MyButton(
                            text = "Back to Home",
                            onClick = {
                                navController.navigate(AppRoute.MAIN) {
                                    popUpTo(AppRoute.MAIN) {
                                        inclusive = false
                                    }
                                }
                            }
                        )
                    }
                }
            }}}
    }
}