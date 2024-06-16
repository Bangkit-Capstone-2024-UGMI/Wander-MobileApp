package academy.bangkit.wander.presentation.myplan.home

import academy.bangkit.wander.R
import academy.bangkit.wander.app.widgets.MyTopAppBar
import academy.bangkit.wander.presentation.ViewModelFactory
import academy.bangkit.wander.presentation.myplan.home.widgets.PlanList
import academy.bangkit.wander.presentation.myplan.home.widgets.SearchBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun MyPlanScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: HomePlanViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )
    val planList by viewModel.getPlanList().observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            MyTopAppBar(title = "My Plan")
        }
    ) {
        paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Image(
                    painter = painterResource(id = R.drawable.banner_plan),
                    contentDescription = "Banner Plan",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 120.dp, start = 16.dp, end = 16.dp)
                ) {
                    Box (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                    ) {
                        SearchBox()
                    }
                    PlanList(planList, navController)
                }
            }
    }
}