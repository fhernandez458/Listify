package com.fhzapps.listify.domain

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fhzapps.listify.data.ListDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private var dao: ListDao

) : ViewModel() {
    private val _listItems = mutableStateListOf<ListItem>()
    
    init {
        viewModelScope.launch(Dispatchers.IO) {
            dao.getAllItems().collect {
                _listItems.clear()
                _listItems.addAll(it)
            }
        }
    }

    @Synchronized
    fun addItem(item: ListItem) {
        _listItems.add(item)
        viewModelScope.launch(Dispatchers.IO) {
            dao.upsertListItem(item)
        }
        Log.d("ListViewModel", "Item added: $item")
    }

    fun itemCount(): Int {
        return _listItems.size
    }

    fun removeItem(item: ListItem) {
        Log.d("ListViewModel", "item size: ${_listItems.size}")
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteListItem(item)
        }
    }


    fun toggleItem(item: ListItem) {
//        _listItems[index] = _listItems[index].copy(isChecked = !_listItems[index].isChecked)
        viewModelScope.launch(Dispatchers.IO) {
            dao.updateListItem(item.copy(isChecked = !item.isChecked))
        }
    }

    fun updateItem(oldItem: ListItem, newItem: ListItem) {

        viewModelScope.launch(Dispatchers.IO) {
            dao.updateListItem(newItem)
        }
    }

    fun getCheckedItems(): Flow<List<ListItem>> {
        return dao.getCheckedItems()
    }

    fun getUncheckedItems(): Flow<List<ListItem>> {
       return dao.getUncheckedItems()

    }
}
