package com.bangkit.wander.presentation.saved

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PlaceDetailScreen(navController: NavHostController) {
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
                    painter = painterResource(id = R.drawable.location2),
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
                Text(text = "Hotel Artotel", fontSize = 22.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Yogyakarta", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Jalan Kaliurang KM. 5,6 No.14, Manggung, Caturtunggal, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55281", fontSize = 16.sp)
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
                        Text(text = "Remove")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}