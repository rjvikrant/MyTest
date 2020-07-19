package com.example.android.mytest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.mytest.data.database.entities.RowsData
import com.example.android.mytest.data.database.entities.RowsDataDao

@Database(
    entities = [RowsData::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun rowsDataDao(): RowsDataDao


    companion object {

        private var instance: AppDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {

            instance ?: buildDatabase(context).also {
                instance = it
            }

        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "MyDatabase"
        ).fallbackToDestructiveMigration()
            .build()


    }
}