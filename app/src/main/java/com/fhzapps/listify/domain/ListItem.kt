package com.fhzapps.listify.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListItem(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val name: String = "",
    val description: String = "",
    val isChecked: Boolean = false
)