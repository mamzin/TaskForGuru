package ru.mamzin.taskforguru.repository

import ru.mamzin.taskforguru.net.RetrofitService

class NetRepository(private val retrofitService: RetrofitService) {

    fun getAllStrings() = retrofitService.getStrings()

}