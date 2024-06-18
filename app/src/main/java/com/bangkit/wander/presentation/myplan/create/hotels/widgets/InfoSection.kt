package com.bangkit.wander.presentation.myplan.create.hotels.widgets

import com.bangkit.wander.app.theme.AppColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoSection() {
    Box(
        modifier = Modifier
            .background(color = AppColor.PrimaryBlue, shape = RoundedCornerShape(size = 16.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Outlined.Info, contentDescription = "info", tint = AppColor.PrimaryYellow )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Discover Hotels Near Your Destination",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
               )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Explore hotels conveniently located around your chosen destination. Our app finds the perfect accommodation options near the midpoint of your trip, ensuring a comfortable stay wherever you go. Distance shown is from the midpoint of your trip.",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.White,
                )
            )
        }

    }
}