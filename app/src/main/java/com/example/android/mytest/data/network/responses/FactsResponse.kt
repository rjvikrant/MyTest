package com.example.android.mytest.data.network.responses

import com.example.android.mytest.data.database.entities.RowsData

data class FactsResponse (
    val title:String,
    val rows:List<RowsData>
    )
