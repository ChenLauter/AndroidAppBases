package com.lauter.androidappbases.base.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseRepository {

    protected suspend fun<T> withIO(block: suspend ()-> T) : T {
        return withContext(Dispatchers.IO) {
            block.invoke()
        }
    }
}