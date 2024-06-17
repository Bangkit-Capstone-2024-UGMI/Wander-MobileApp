package academy.bangkit.wander.presentation.profile


import academy.bangkit.wander.app.widgets.MyTopAppBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ProfileEmailScreen(navController: NavHostController) {
    val profileViewModel: ProfileViewModel = viewModel()
    val currentUser by profileViewModel.currentUser.observeAsState()
    val userEmail = currentUser?.email

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Profile",
                showBackButton = true,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = userEmail ?: "Email not available",
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileEmailScreenPreview() {
    val navController = rememberNavController()
    ProfileEmailScreen(navController = navController)
}