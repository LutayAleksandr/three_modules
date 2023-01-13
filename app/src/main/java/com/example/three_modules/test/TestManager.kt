package com.example.three_modules.test

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestManager @Inject constructor(
    private val context: Context,
    private val testUtils: TestUtils
){

    fun helloWorldFromManager(): String = "hello, testManager!"
}