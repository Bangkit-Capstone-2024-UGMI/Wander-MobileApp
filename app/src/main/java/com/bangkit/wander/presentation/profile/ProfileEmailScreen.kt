package com.bangkit.wander.presentation.profile


import androidx.compose.foundation.layout.Arrangement
import com.bangkit.wander.app.widgets.MyTopAppBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bangkit.wander.presentation.ViewModelFactory

@Composable
fun ProfileEmailScreen(navController: NavHostController) {
    val context = LocalContext.current
    val profileViewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )
    val currentUser by profileViewModel.currentUser.observeAsState()

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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "Email",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    currentUser?.email?.let { email ->
                        Text(
                            text = email,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}