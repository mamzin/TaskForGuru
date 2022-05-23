package ru.mamzin.taskforguru.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mamzin.taskforguru.repository.NetRepository

class ViewModelHomeStoreFactory constructor(private val repository: NetRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HotSalesViewModel::class.java)) {
            HotSalesViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}