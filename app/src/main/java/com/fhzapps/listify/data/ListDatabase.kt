package com.fhzapps.listify.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fhzapps.listify.domain.ListItem


@Database(
    entities = [ListItem::class],
    version = 1
)
abstract class ListDatabase : RoomDatabase() {

    abstract val dao: ListDao

}