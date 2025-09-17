package com.fhzapps.listify.domain

import com.fhzapps.listify.data.ListItem

data class ListViewUiState (
    val listItems: List<ListItem> = emptyList(),
    val checkedItems: List<ListItem> = emptyList(),
    val uncheckedItems: List<ListItem> = emptyList()

)