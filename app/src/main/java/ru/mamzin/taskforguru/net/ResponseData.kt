package ru.mamzin.taskforguru.net

data class ResponseData<T>(
    val home_store: T,
    val best_seller: T
)