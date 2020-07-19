package com.example.android.mytest

import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.listener.InstrumentationRunListener
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.mytest.data.database.AppDatabase
import com.example.android.mytest.data.database.entities.RowsData
import com.example.android.mytest.data.database.entities.RowsDataDao
import com.example.android.mytest.util.Coroutines
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DbTest {

    private lateinit var db: AppDatabase
    private lateinit var dao : RowsDataDao

    @Before
    fun createDb(){

val context = InstrumentationRegistry.getInstrumentation().context
db = Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java)
    .allowMainThreadQueries()
    .build()
        dao = db.rowsDataDao()

    }

    @Test
    fun insertData(){



        val list = listOf<RowsData>(
            RowsData(description = "desc", imageHref ="img",title = "title"),
            RowsData(description = "desc", imageHref =null,title = "title"),
            RowsData(description = "desc", imageHref ="img",title = null),
            RowsData(description = null, imageHref =null,title = null))
        Coroutines.main { db.rowsDataDao().saveAllQuotes(list) }
    }



    @After
    fun closeDb(){
        db.close()
        }

}