package com.example.imdbmoviesapp.core.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


suspend fun <T> runOnIOThread(block: suspend () -> T): T = withContext(Dispatchers.IO){block()}
suspend fun <T> runOnDefaultThread(block: suspend () -> T): T = withContext(Dispatchers.Default){block()}