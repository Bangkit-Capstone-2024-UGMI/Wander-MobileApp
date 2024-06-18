package com.bangkit.wander.presentation.myplan.create.hotels

import com.bangkit.wander.app.navigation.AppRoute
import com.bangkit.wander.app.theme.AppColor
import com.bangkit.wander.app.widgets.MyButton
import com.bangkit.wander.app.widgets.MyTopAppBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@Composable
fun HotelDetailScreen(
    navController: NavHostController
) {
    Scaffold (
        topBar = {
             MyTopAppBar(title = "Hotel Detail", showBackButton = true, onBackClick = { navController.popBackStack() })
        },
        bottomBar = {
            Box(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                MyButton(
                    text = "Select Hotel",
                    onClick = {navController.navigate(AppRoute.SUCCESS_CREATE) {
                        popUpTo(AppRoute.MAIN) {
                            inclusive = false
                        }
                    } }
                )
            }
        }
    ) {
        paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                LazyColumn(){
                    item {
                        AsyncImage(
                            model = "https://asset-a.grid.id/crop/0x0:0x0/945x630/photo/2023/06/18/staycationjpg-20230618013836.jpg",
                            contentDescription = "Hotel Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(RectangleShape)
                        )
                    }
                    item {
                        Box(modifier = Modifier.padding(16.dp)){
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = "Hotel Name",
                                        style = TextStyle(
                                            fontSize = 22.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = AppColor.PrimaryDark,
                                        )
                                    )
                                    Spacer(modifier = Modifier.padding(4.dp))
                                    Text(
                                        text = "X KM from the midpoint",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            color = AppColor.PrimaryDark,
                                        )
                                    )
                                    Spacer(modifier = Modifier.padding(4.dp))
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
                                }
                                Icon(
                                    imageVector = Icons.Outlined.FavoriteBorder,
                                    contentDescription = "Favorite",
                                    tint = AppColor.PrimaryDark
                                )
                            }
                        }
                        // divider
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 6.dp),
                            thickness = 4.dp,
                            color = AppColor.PrimaryDarkVariant.copy(
                                alpha = 0.2f
                            )
                        )
                    }
                    item {
                        Box(modifier = Modifier.padding(16.dp)){
                            Column() {
                                Text(
                                    text = "Location",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = AppColor.PrimaryDark,
                                    )
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(
                                    text = "Lunang Selatan, South Pesisir Regency, West Sumatra",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(400),
                                        color = AppColor.PrimaryDarkVariant,
                                    )
                                )
                            }
                        }
                    }

                }
            }
    }
}