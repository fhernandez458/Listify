package com.fhzapps.listify.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fhzapps.listify.data.ListDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private var dao: ListDao

) : ViewModel() {
    @Synchronized
    fun addItem(item: ListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.upsertListItem(item)
        }
    }

    fun itemCount(): Flow<Int> {
        return dao.getAllItems().map { it.size }
    }

    fun removeItem(item: ListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteListItem(item)
        }
    }


    fun toggleItem(item: ListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.updateListItem(item.copy(isChecked = !item.isChecked))
        }
    }

    fun updateItem(oldItem: ListItem, newItem: ListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.updateListItem(oldItem.copy(name = newItem.name, description = newItem.description))
        }
    }

    fun getCheckedItems(): Flow<List<ListItem>> {
        return dao.getCheckedItems()
    }

    fun getUncheckedItems(): Flow<List<ListItem>> {
       return dao.getUncheckedItems()
    }

}
