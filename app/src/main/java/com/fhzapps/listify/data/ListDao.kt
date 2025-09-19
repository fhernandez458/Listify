package com.fhzapps.listify.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.fhzapps.listify.domain.ListItem
import kotlinx.coroutines.flow.Flow


@Dao
interface ListDao {

    @Query("SELECT * FROM listItem")
    fun getAllItems(): Flow<List<ListItem>>

    @Upsert // combo of insert and update
    suspend fun upsertListItem(listItem: ListItem)

    @Delete
    suspend fun deleteListItem(listItem: ListItem)

    @Query("SELECT * FROM listItem WHERE id = :id")
    suspend fun getListItemById(id: String): ListItem

    @Update
    suspend fun updateListItem(listItem: ListItem)

    @Query("SELECT * FROM listitem WHERE isChecked = :isChecked")
    fun getCheckedItems(isChecked: Boolean = true): Flow<List<ListItem>>

    @Query("SELECT * FROM listItem WHERE isChecked = :isChecked")
    fun getUncheckedItems(isChecked: Boolean = false): Flow<List<ListItem>>

    @Query("SELECT * FROM listItem ORDER BY name ASC")
    fun getAllItemsAlphabetical() : Flow<List<ListItem>>

}