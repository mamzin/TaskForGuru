package ru.mamzin.taskforguru.model

data class ModelResponseStore<T>(
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)