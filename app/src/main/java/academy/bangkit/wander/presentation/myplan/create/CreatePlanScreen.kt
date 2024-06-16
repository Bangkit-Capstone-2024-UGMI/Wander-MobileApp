package academy.bangkit.wander.presentation.myplan.create

import academy.bangkit.wander.app.widgets.MyTopAppBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun CreatePlanScreen(navController: NavHostController) {
    Scaffold (
        topBar = {
            MyTopAppBar(
                title = "Create a New Plan",
                showBackButton = true,
                onBackClick = { navController.popBackStack() })
        }
    ){
        paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Column {
                    Text(text = "Create a New Plan")
                }
            }
    }
}