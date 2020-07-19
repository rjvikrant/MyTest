package com.example.android.mytest.util

import kotlinx.coroutines.*

fun <T> lazyDeffred(block : suspend CoroutineScope.()-> T ) :Lazy<Deferred<T>>{

    return lazy {
        GlobalScope.async(start =  CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }

}