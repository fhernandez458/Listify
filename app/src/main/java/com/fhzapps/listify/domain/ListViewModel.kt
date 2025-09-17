package com.fhzapps.listify.domain

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.fhzapps.listify.data.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor() : ViewModel() {
    private val _listItems = mutableStateListOf<ListItem>()
    val listItems = _listItems.asFlow()

//    private val _uiState = MutableStateFlow(ListViewUiState())
//    val uiState : StateFlow<ListViewUiState> = _uiState.asStateFlow()

    @Synchronized
    fun addItem(item: ListItem) {
        _listItems.add(item)

        Log.d("ListViewModel", "Item added: $item")
    }

    fun itemCount(): Int {
        return _listItems.size
    }

    fun removeItem(item: ListItem) {
        Log.d("ListViewModel", "item size: ${_listItems.size}")
        _listItems.remove(item)
    }

    fun toggleItem(item: ListItem) {
        val index = _listItems.indexOf(item)
        _listItems[index] = _listItems[index].copy(isChecked = !_listItems[index].isChecked)
    }

    fun updateItem(oldItem: ListItem, newItem: ListItem) {
        val index = _listItems.indexOf(oldItem)
        _listItems[index] = newItem
    }

    fun getCheckedItems(): List<ListItem> {
        Log.d("ListViewModel", "Checked items: ${_listItems.filter { it.isChecked }}")
        return _listItems.filter { it.isChecked }
    }

    fun getUncheckedItems(): List<ListItem> {
        Log.d("ListViewModel", "Unchecked items: ${_listItems.filter { !it.isChecked }}")
        return _listItems.filter { !it.isChecked }
    }
}
