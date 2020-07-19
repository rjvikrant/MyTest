package com.example.android.mytest.util

import java.io.IOException

class ApiExceptions(message: String): IOException(message)
class NoIntenetException(message: String): IOException(message)