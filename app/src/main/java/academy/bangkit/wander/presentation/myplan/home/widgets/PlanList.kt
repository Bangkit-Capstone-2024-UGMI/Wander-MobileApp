package academy.bangkit.wander.presentation.myplan.home.widgets

import academy.bangkit.wander.app.navigation.AppRoute
import academy.bangkit.wander.data.model.Plan
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PlanList (itemsData: List<Plan>, navController: NavHostController) {
    Column {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Plan List", style = TextStyle(fontSize = 20.sp))
            TextButton(onClick = { navController.navigate(AppRoute.CREATE_PLAN) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icon" )
                Text(text = "Add New Plan")
            }
        }
        if (itemsData.isEmpty()) {
            EmptyStatePlan()
            return
        }
        LazyColumn {
            items(itemsData.size) { index ->
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    PlanCard(data = itemsData[index])
                }
            }
        }
    }
}