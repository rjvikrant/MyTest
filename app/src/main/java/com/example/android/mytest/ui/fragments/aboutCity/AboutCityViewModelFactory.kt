package com.example.android.mytest.ui.fragments.aboutCity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.mytest.data.repositiory.RowsDataRepository
import java.lang.ClassCastException

@Suppress("UNCHECKED_CAST")
class AboutCityViewModelFactory(private val repository: RowsDataRepository ):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AboutCityViewModel::class.java)){

            return AboutCityViewModel(repository) as T
        }
        throw ClassCastException("Invalid ViewModel Class")
    }
}