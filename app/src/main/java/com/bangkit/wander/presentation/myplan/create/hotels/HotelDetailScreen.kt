package com.bangkit.wander.presentation.myplan.create.hotels

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bangkit.wander.data.local.TemporaryData
import com.bangkit.wander.presentation.myplan.create.hotels.widgets.ConfirmationDialog
import com.bangkit.wander.presentation.myplan.create.hotels.widgets.HotelLocationMap
import com.bangkit.wander.presentation.myplan.create.hotels.widgets.PlacePhotosWidget
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient

@Composable
fun HotelDetailScreen(
    navController: NavHostController
) {
    val hotel = TemporaryData.hotelDetail
    val sourceHotel = TemporaryData.sourceHotel
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
             MyTopAppBar(title = "Hotel Detail", showBackButton = true, onBackClick = { navController.popBackStack() })
        },
        bottomBar = {
            if (sourceHotel == "hotelList") {
                Box(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    MyButton(
                        text = "Select Hotel",
                        onClick = {setShowDialog(true)}
                    )
                }
            }
        }
    ) {
        paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                ConfirmationDialog(
                    showDialog = showDialog,
                    onDismiss = { setShowDialog(false) },
                    onConfirm = {
                        TemporaryData.newPlan?.hotel = TemporaryData.hotelDetail
                        navController.navigate(AppRoute.SUCCESS_CREATE) {
                            popUpTo(AppRoute.MAIN) {
                                inclusive = false
                            }
                        }
                    },
                    title = "Confirm Action",
                    text = "Are you sure you want to create plan with this hotel?",
                    confirmButtonText = "Yes",
                    dismissButtonText = "No"
                )
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
                                Column (
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = hotel?.name ?: "Hotel Name",
                                        style = TextStyle(
                                            fontSize = 22.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = AppColor.PrimaryDark,
                                        )
                                    )
                                    Spacer(modifier = Modifier.padding(4.dp))
                                    val formattedDistance = String.format("%.2f", hotel?.distance?: 0.0)
                                    Text(
                                        text = "$formattedDistance KM from the midpoint",
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
                                            text = hotel?.rating?.toString() ?: "0.0",
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
                                    text = hotel?.formattedAddress ?: "Hotel Address",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(400),
                                        color = AppColor.PrimaryDarkVariant,
                                    )
                                )
                                Spacer(modifier = Modifier.padding(8.dp))

                                if (hotel != null) {
                                    HotelLocationMap(hotel.placeId)
                                }
                                
                                Spacer(modifier = Modifier.padding(8.dp))

                                Text(
                                    text = "Photos",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = AppColor.PrimaryDark,
                                    )
                                )

                                Spacer(modifier = Modifier.padding(4.dp))

                                if(hotel != null){
                                    PlacePhotosWidget(hotel.placeId)
                                }
                            }
                        }
                    }

                }
            }
    }
}
