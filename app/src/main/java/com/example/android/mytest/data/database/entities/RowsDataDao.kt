package com.example.android.mytest.data.database.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RowsDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun saveAllQuotes(quotes: List<RowsData>)


    @Query("select * from RowsData")
    fun getQuotes(): LiveData<List<RowsData>>
}