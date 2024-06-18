package com.bangkit.wander.presentation.search

import androidx.compose.foundation.BorderStroke
import com.bangkit.wander.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bangkit.wander.app.theme.AppColor


@Composable
fun LocationDetailScreen(navController : NavHostController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "Hotel Image",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = {navController.popBackStack()},
                    modifier = Modifier
                        .padding(16.dp)
                        .size(48.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                        .align(Alignment.TopStart)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "Hotel Kurniawan", fontSize = 32.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Cianjur", fontSize = 22.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Star, contentDescription ="rating", tint = AppColor.PrimaryYellow )
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        text = "5.0",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = AppColor.PrimaryBlue,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Buttons for directions
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { /* Handle directions click */ },
                        modifier = Modifier
                            .height(50.dp)
                            .width(140.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF4D160)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Directions")
                    }

                    OutlinedButton(
                        onClick = { /* Handle start directions click */ },
                        modifier = Modifier
                            .width(90.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Color(0xFFF4D160)),  // Custom outline color
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color(0xFFF4D160)  // Custom text color
                        )
                    ) {
                        Text(text = "Start")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlaceDetailScreen() {
    val navController = rememberNavController()
    LocationDetailScreen(navController = navController)
}