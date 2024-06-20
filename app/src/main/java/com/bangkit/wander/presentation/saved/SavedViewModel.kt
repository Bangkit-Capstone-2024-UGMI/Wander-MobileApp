package com.bangkit.wander.presentation.saved

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.bangkit.wander.R
import com.bangkit.wander.presentation.saved.widgets.SavedCardItem

class SavedViewModel : ViewModel() {
    private val _items = mutableStateListOf(
        SavedCardItem(1, "Favorites", R.drawable.star),
        SavedCardItem(2, "Want to Go", R.drawable.calendar),
        SavedCardItem(3, "Travel", R.drawable.plane)
    )

    val items: List<SavedCardItem>
        get() = _items

    fun addItem(item: SavedCardItem) {
        _items.add(item)
    }

    fun updateItem(updatedItem: SavedCardItem) {
        val index = _items.indexOfFirst { it.id == updatedItem.id }
        if (index != -1) {
            _items[index] = updatedItem
        }
    }

    fun deleteItem(item: SavedCardItem) {
        _items.remove(item)
    }

    fun clearItems() {
        _items.clear()
    }
}