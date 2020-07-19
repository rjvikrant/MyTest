package com.example.android.mytest.ui

import android.app.Application
import com.example.android.mytest.data.database.AppDatabase
import com.example.android.mytest.data.network.ApiCalls
import com.example.android.mytest.data.network.NetworkIntercepter
import com.example.android.mytest.data.preferences.PreferenceProvider
import com.example.android.mytest.data.repositiory.RowsDataRepository
import com.example.android.mytest.ui.fragments.aboutCity.AboutCityViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class AppClass : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {

        import(androidXModule(this@AppClass))
        bind() from singleton { NetworkIntercepter(this@AppClass) }
        bind() from singleton { ApiCalls(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { RowsDataRepository(instance(), instance(), instance()) }
        bind() from singleton { AboutCityViewModelFactory(instance()) }


    }
}