package com.example.android.mytest.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RowsData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String?,
    val imageHref: String?,
    val title: String?
)