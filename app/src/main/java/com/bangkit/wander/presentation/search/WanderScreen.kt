package com.bangkit.wander.presentation.search

import com.bangkit.wander.presentation.search.widgets.BottomWidgetContent
import com.bangkit.wander.presentation.search.maps.MapScreen
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bangkit.wander.presentation.ViewModelFactory
import com.bangkit.wander.presentation.myplan.create.CreatePlanViewModel
import com.bangkit.wander.presentation.search.maps.MapState
import com.bangkit.wander.presentation.search.maps.MapViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun WanderScreen() {
    val context = LocalContext.current
    val viewModel: MapViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState()
    )
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomWidgetContent(
                onExpand = {
                    scope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                },
            )
        },
        sheetPeekHeight = 120.dp,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) {
                MapScreen()
                SearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    query = text,
                    onQueryChange = { text = it },
                    onSearch = { active = false },
                    active = active,
                    onActiveChange = { active = it },
                    placeholder = { Text(text = "Where are we wandering today?", fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    trailingIcon = {
                        if (active) {
                            Icon(
                                modifier = Modifier.clickable {
                                    if (text.isNotEmpty()) {
                                        text = ""
                                    } else {
                                        active = false
                                    }
                                },
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close"
                            )
                        }
                    }
                ){
                    // searchbar content
                }
            }
        }
    }
}