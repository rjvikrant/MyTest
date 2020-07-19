package com.example.android.mytest.data.repositiory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.mytest.data.database.AppDatabase
import com.example.android.mytest.data.database.entities.RowsData
import com.example.android.mytest.data.network.ApiCalls
import com.example.android.mytest.data.network.SafeApiRequests
import com.example.android.mytest.data.preferences.PreferenceProvider
import com.example.android.mytest.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val minimumInterval = 6

class RowsDataRepository(
    private val api: ApiCalls,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequests() {


    private val quotes = MutableLiveData<List<RowsData>>()


    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }


    suspend fun getQuotes(): LiveData<List<RowsData>> {
        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.rowsDataDao().getQuotes()
        }

    }

    private suspend fun fetchQuotes() {
        val lastSavedAt = prefs.getLastSavedAt()
        lastSavedAt?.let {
        Log.e("lastsaveat",""+LocalDateTime.parse(lastSavedAt))
        }
        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            val response = apiRequest { api.getListData() }
           // Log.d("resp", response.toString())
          //  Log.e("lastsaveat",""+LocalDateTime.parse(lastSavedAt))
            quotes.postValue(response.rows)
        }

    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > minimumInterval
    }


    private fun saveQuotes(quotes: List<RowsData>) {
        Coroutines.io {

            prefs.saveLastSavedAt(LocalDateTime.now().toString())

            Log.e("saving time",""+LocalDateTime.now())
            db.rowsDataDao().saveAllQuotes(quotes)
        }

    }

}
