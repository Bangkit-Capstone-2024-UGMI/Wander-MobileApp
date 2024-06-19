package com.bangkit.wander.presentation.saved

import com.bangkit.wander.R
import com.bangkit.wander.app.widgets.MyTopAppBar
import com.bangkit.wander.presentation.saved.widgets.SavedCardItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bangkit.wander.app.navigation.AppRoute
import com.bangkit.wander.app.widgets.MyButton
import com.bangkit.wander.presentation.ViewModelFactory
import com.bangkit.wander.presentation.saved.widgets.SavedCard

@Composable
fun SavedScreen(navController: NavHostController) {
    val context = LocalContext.current
    val savedViewModel: SavedViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )
    val items = savedViewModel.items
    val showDialog = remember { mutableStateOf(false) }
    val showEditDialog = remember { mutableStateOf(false) }
    val showDeleteDialog = remember { mutableStateOf(false) }
    val itemToEdit = remember { mutableStateOf<SavedCardItem?>(null) }
    val itemToDelete = remember { mutableStateOf<SavedCardItem?>(null) }

    val availableIcons = remember {
        listOf(
            R.drawable.star,
            R.drawable.calendar,
            R.drawable.plane,
            R.drawable.add
        )
    }

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Saved Places",
                showBackButton = false
                )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(16.dp))

            NewListButton {
                showDialog.value = true
            }

            Spacer(modifier = Modifier.size(22.dp))

            if (items.isEmpty()) {
                EmptyStateView()
            } else {
                CardList(items = items, navController = navController) { item, action ->
                    when (action) {
                        "edit" -> {
                            itemToEdit.value = item
                            showEditDialog.value = true
                        }
                        "delete" -> {
                            itemToDelete.value = item
                            showDeleteDialog.value = true
                        }
                    }
                }
            }

            if (showDialog.value) {
                AddCardDialog(
                    availableIcons = availableIcons,
                    onAdd = { newTitle, selectedIcon ->
                        val newCard = SavedCardItem(
                            id = items.size + 1,
                            title = newTitle,
                            iconResource = selectedIcon
                        )
                        savedViewModel.addItem(newCard)
                        showDialog.value = false
                    },
                    onDismiss = { showDialog.value = false }
                )
            }

            if (showEditDialog.value) {
                itemToEdit.value?.let { item ->
                    EditListDialog(
                        currentTitle = item.title,
                        currentIcon = item.iconResource,
                        availableIcons = availableIcons,
                        onEdit = { newTitle, selectedIcon ->
                            val updatedItem = item.copy(title = newTitle, iconResource = selectedIcon)
                            savedViewModel.updateItem(updatedItem)
                            showEditDialog.value = false
                        },
                        onDismiss = { showEditDialog.value = false }
                    )
                }
            }

            if (showDeleteDialog.value) {
                DeleteConfirmationDialog(
                    onConfirm = {
                        itemToDelete.value?.let {
                            savedViewModel.deleteItem(it)
                            showDeleteDialog.value = false
                        }
                    },
                    onDismiss = { showDeleteDialog.value = false }
                )
            }
        }
    }
}



@Composable
fun NewListButton(onClick: () -> Unit) {
    MyButton(
        text = "Create New List",
        onClick = onClick,
        icon = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon"
            )
        },
        isSecondary = true
    )
}

@Composable
fun CardList(
    items: List<SavedCardItem>,
    navController: NavHostController,
    onItemAction: (SavedCardItem, String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items) { item ->
            SavedCard(
                item = item,
                onClick = {
                    navController.navigate(AppRoute.PLACE_LIST)
                },
                onEdit = { onItemAction(it, "edit") },
                onDelete = { onItemAction(it, "delete") }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun EmptyStateView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_state_plan),
            contentDescription = "No lists available",
            modifier = Modifier.size(120.dp)
        )
        Text("No lists available. Create a new one!")
    }
}







