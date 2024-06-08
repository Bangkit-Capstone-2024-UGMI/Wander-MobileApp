package academy.bangkit.wander.presentation.myplan

import academy.bangkit.wander.app.widgets.MyTopAppBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyPlanScreen() {
    Scaffold(
        topBar = {
            MyTopAppBar(title = "My Plan")
        }
    ) {
        paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {

            }
    }
}