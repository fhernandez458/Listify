package com.fhzapps.listify.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListEntity (
    @PrimaryKey val id : String = "",
    val name : String = "",
    val description : String = "",
    val isChecked : Boolean = false
)