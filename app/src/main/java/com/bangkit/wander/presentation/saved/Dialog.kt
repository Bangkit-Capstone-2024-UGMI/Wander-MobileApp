package com.bangkit.wander.presentation.saved

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun EditListDialog(
    currentTitle: String,
    currentIcon: Int,
    availableIcons: List<Int>,
    onEdit: (String, Int) -> Unit,
    onDismiss: () -> Unit
) {
    var updatedTitle by remember { mutableStateOf(currentTitle) }
    var selectedIcon by remember { mutableStateOf(currentIcon) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Edit List") },
        text = {
            Column {
                TextField(
                    value = updatedTitle,
                    onValueChange = { updatedTitle = it },
                    label = { Text("List Name") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Select an Icon")
                LazyRow {
                    items(availableIcons) { icon ->
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .padding(4.dp)
                                .clickable {
                                    selectedIcon = icon
                                }
                                .border(
                                    if (selectedIcon == icon) 2.dp else 0.dp,
                                    if (selectedIcon == icon) Color.Blue else Color.Transparent
                                )
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (updatedTitle.isNotBlank()) {
                        onEdit(updatedTitle, selectedIcon)
                    }
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun DeleteConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Delete List") },
        text = { Text("Are you sure you want to delete this list?") },
        confirmButton = {
            Button(onClick = { onConfirm() }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun AddCardDialog(
    availableIcons: List<Int>,
    onAdd: (String, Int) -> Unit,
    onDismiss: () -> Unit
) {
    var newCardTitle by remember { mutableStateOf("") }
    var selectedIcon by remember { mutableStateOf(availableIcons[0]) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Create New List") },
        text = {
            Column {
                TextField(
                    value = newCardTitle,
                    onValueChange = { newCardTitle = it },
                    label = { Text("List Name") }
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text("Select an Icon")
                LazyRow {
                    items(availableIcons) { icon ->
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .padding(4.dp)
                                .clickable {
                                    selectedIcon = icon
                                }
                                .border(
                                    if (selectedIcon == icon) 2.dp else 0.dp,
                                    if (selectedIcon == icon) Color.Blue else Color.Transparent
                                )
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (newCardTitle.isNotBlank()) {
                        onAdd(newCardTitle, selectedIcon)
                    }
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}