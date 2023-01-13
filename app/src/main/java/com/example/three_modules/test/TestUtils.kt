package com.example.three_modules.test

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestUtils @Inject constructor(
    private val context: Context
) {

    fun helloWorld(): String = "Hello, world"
}